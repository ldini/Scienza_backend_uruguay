package ar.com.scienza.config;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import ar.com.scienza.base.Authorize;
import ar.com.scienza.entity.Afiliado;
import ar.com.scienza.entity.Rol;
import ar.com.scienza.entity.Sesion;
import ar.com.scienza.entity.Transaction;
import ar.com.scienza.enumerator.RolEnum;
import ar.com.scienza.enumerator.TipoDispositivoEnum;
import ar.com.scienza.exception.CustomServiceException;
import ar.com.scienza.exception.SecurityException;
import ar.com.scienza.repository.SesionRepository;
import ar.com.scienza.repository.TransaccionRepository;


public class ScienzaSecurityInterceptor implements HandlerInterceptor {

	public static final Logger logger = Logger.getLogger(ScienzaSecurityInterceptor.class);
	
    public static final String APPID_ERROR = "AppID inválido";
    public static final String TOKEN_ERROR = "Token inválido";
    public static final String DEVTYP_ERROR = "DeviceType inválido";
    public static final String SAPID_ERROR = "Identificador SAP inválido";
    public static final String ROLE_ERROR = "Usuario con permisos insuficientes";
    public static final String SECURITY_ERROR = "Request Header inválido";
    
	@Autowired
	private SesionRepository sesionRepository;

	@Autowired
	private TransaccionRepository transaccionRepository;
	
	@Autowired
	private Transaction transaction;
    
    @Value("${server.appId}")
    String appId;
    
    
    /**
     * Se valida las siguientes propiedades del header:
     *  - AppID valido
     *  - DeviceType valido
     *  - Token valido y correspondiente a DeviceType
     *  - ROL con permisos suficientes de ejecución
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
    	
    	Afiliado afiliado = null;
    	Rol rol = null;
    	
    	try
    	{
    		if(!(obj instanceof HandlerMethod)) 
    			return true;
    		
    		HandlerMethod handlerMethod = (HandlerMethod) obj;
            Method method = handlerMethod.getMethod();
            String grantMethod = method.getAnnotation(Authorize.class).value();
        	
			String controllerName = handlerMethod.getBean().getClass().getSimpleName().replace("Controller", "");
			String actionName = handlerMethod.getMethod().getName();
			
			StringBuffer requestURL = request.getRequestURL();
		    String queryString = request.getQueryString();
		    
		    String endpoint = (queryString == null) ? requestURL.toString() : requestURL.append('?').append(queryString).toString();
		    logger.info("");
		    logger.info("[" + endpoint + "]");
						
	    	if(!appId.equals(request.getHeader("AppId")))
	    		throw new SecurityException(APPID_ERROR);
	    	
	    	if(request.getHeader("Token") == null)
	    		throw new SecurityException(TOKEN_ERROR);
	    	
	    	// Metodos Publicos
	    	if(request.getHeader("Token").equals("-1") && "*".equals(grantMethod)) {
	    		ScienzaLogger.startProcess(controllerName, actionName);
	    		return true;
	    	}
	    	
	    	Sesion sesion = sesionRepository.findByToken(request.getHeader("Token"));
	    	if(sesion == null || sesion.getFechaDelete() != null)
	    		throw new CustomServiceException(3,TOKEN_ERROR);	
    			
	    	if(request.getHeader("DeviceType") == null || !TipoDispositivoEnum.containsCode(request.getHeader("DeviceType")))
	    		throw new SecurityException(DEVTYP_ERROR);
	    	
	    	if(request.getHeader("SapId") == null)
	    		throw new SecurityException(SAPID_ERROR);
	    	
	    	if(RolEnum.AFILIADO.getCodigo().equals(sesion.getRol())) 
	    	{
	    		afiliado = sesion.getAfiliado().getAfiliadoActivo(Long.valueOf(request.getHeader("SapId")));
	    		if(afiliado == null) {
	    			throw new SecurityException(SAPID_ERROR);
	    		}
	    		rol = sesion.getAfiliado().getUsuario().getRol();
	    	}
	    	else if(RolEnum.ADMIN.getCodigo().equals(sesion.getRol())) 
	    	{
	    		if(!"-1".equals(request.getHeader("SapId"))) {
	    			throw new SecurityException(SAPID_ERROR);
	    		}
	    		if(!TipoDispositivoEnum.WEB.getCodigo().equals(request.getHeader("DeviceType"))) {
	    			throw new SecurityException(ROLE_ERROR);
	    		}
	    		rol = sesion.getAdministrador().getUsuario().getRol();
	    	}
	    	else {
	    		throw new SecurityException(SAPID_ERROR);
	    	}
	    	
	    	if(sesion.getAdministrador() != null) {
		    	if(!"*".equals(grantMethod) && !rol.getGrantsToString(sesion.getAdministrador()).contains(method.getAnnotation(Authorize.class).value()))
	            	throw new SecurityException(ROLE_ERROR); 
	    	}
	    	else if(sesion.getAfiliado() != null) {
	    		if(!"*".equals(grantMethod) && !rol.getGrantsToString(afiliado).contains(method.getAnnotation(Authorize.class).value()))
	            	throw new SecurityException(ROLE_ERROR); 
	    	}
	    	else {
	    		throw new SecurityException(ROLE_ERROR); 
	    	}

	    	   	
			ScienzaLogger.startProcess(controllerName, actionName);
			
			this.transaction.setSesion(sesion);
			this.transaction.setController(controllerName);
			this.transaction.setAction(actionName);
			this.transaction.setEndpoint(endpoint);
    	}
    	catch(SecurityException | CustomServiceException e) {
    		logger.error(e);
    		throw e;
    	}
    	catch(Exception e) {
    		logger.error(e);
    		throw new SecurityException(SECURITY_ERROR);
    	}
    	
    	return true;
    }
    
    
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object obj, ModelAndView e) throws Exception {
		// DO-NOTHING
	}


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object obj, Exception e) throws Exception {

		HandlerMethod handlerMethod = (HandlerMethod) obj;
    	
		String controllerName = handlerMethod.getBean().getClass().getSimpleName().replace("Controller", "");
		String actionName = handlerMethod.getMethod().getName();
		
		try
		{
			//Solo se almacenan Tx de una sesion
			if(transaction.getSesion() != null) {
				transaction.createOnRepository(transaccionRepository);
			}
		}
		catch (Exception e1) {}
		
		ScienzaLogger.endProcess(controllerName, actionName);
    }
}
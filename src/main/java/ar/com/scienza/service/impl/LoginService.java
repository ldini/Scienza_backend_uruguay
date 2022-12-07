package ar.com.scienza.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import ar.com.scienza.remote.ws.dto.DTSCIENZAREGUSUARIOUY;
import ar.com.scienza.remote.ws.dto.DTSCIENZAREGUSUARIOUYResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.scienza.config.ScienzaLogger;
import ar.com.scienza.dto.LoginRequestDto;
import ar.com.scienza.dto.LoginResponseDto;
import ar.com.scienza.dto.UserResponseDto;
import ar.com.scienza.entity.Administrador;
import ar.com.scienza.entity.Afiliado;
import ar.com.scienza.entity.Sesion;
import ar.com.scienza.entity.Usuario;
import ar.com.scienza.enumerator.RolEnum;
import ar.com.scienza.enumerator.SAPResultadoEnum;
import ar.com.scienza.enumerator.TipoDispositivoEnum;
import ar.com.scienza.exception.ServiceException;
import ar.com.scienza.remote.ws.service.AffiliateBindingRemoteService;
import ar.com.scienza.repository.AdministradorRepository;
import ar.com.scienza.repository.AfiliadoRepository;
import ar.com.scienza.repository.SesionRepository;
import ar.com.scienza.repository.UsuarioRepository;
import ar.com.scienza.service.ILoginService;


@Service
@Transactional(rollbackFor=ServiceException.class)
public class LoginService implements ILoginService {

	@Autowired
	private AdministradorRepository administradorRepository;
	
	@Autowired
	private AfiliadoRepository afiliadoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private SesionRepository sesionRepository;

	@Autowired
	private AffiliateBindingRemoteService affiliateBindingRemoteService;

	
	@Override
	public LoginResponseDto login(LoginRequestDto request, String token, Long sapId, String deviceType) throws ServiceException {

		LoginResponseDto response = new LoginResponseDto();
		
		Administrador administrador = null;
		Afiliado afiliado = null;
		Sesion sesion = null;
		UserResponseDto userActualResponse = null;
		UserResponseDto userVinculadoResponse = null;
		List<UserResponseDto> userVinculadoResponseList = new ArrayList<UserResponseDto>();
	
		try 
		{
			Usuario usuario = usuarioRepository.findByUserNameAndPassword(request.getUserName(), request.getPassword());
			
			if(usuario == null) {
				throw new ServiceException("usuario o contraseña incorrecto");
			}
			
			String rol = usuario.getRol().getNombre();
			
			if(rol.equals(RolEnum.ADMIN.getCodigo()) && !deviceType.equals(TipoDispositivoEnum.WEB.getCodigo())) {
				throw new ServiceException("usuario no permitido");
			}
			
			if(RolEnum.ADMIN.getCodigo().equals(rol))
			{
				administrador = administradorRepository.findByUsuarioId(usuario.getId());			
				sesion = new Sesion();
				sesion.setToken(UUID.randomUUID().toString().toUpperCase());
				sesion.setTipoDispositivo(deviceType);
				sesion.setRol(rol);
				sesion.setAdministrador(administrador);
				sesion.createOnRepository(sesionRepository);
				
				userActualResponse = new UserResponseDto();
				userActualResponse.setAvatar(administrador.getAvatar());
				userActualResponse.setNombre(administrador.getNombre());
				userActualResponse.setApellido(administrador.getApellido());
				userActualResponse.setcedulaIdentidad(null);
				userActualResponse.setSapId(null);
				userActualResponse.setVerificarPerfil(false);
				userActualResponse.setPermisos(usuario.getRol().getGrantsToString(administrador));
			}
			else if(RolEnum.AFILIADO.getCodigo().equals(rol))
			{
				afiliado = afiliadoRepository.findByUsuarioId(usuario.getId());
				sesion = new Sesion();
				sesion.setToken(UUID.randomUUID().toString().toUpperCase());
				sesion.setTipoDispositivo(deviceType);
				sesion.setRol(rol);
				sesion.setAfiliado(afiliado);
				sesion.createOnRepository(sesionRepository);

				userActualResponse = new UserResponseDto();
				userActualResponse.setAvatar(afiliado.getAvatar());
				userActualResponse.setNombre(afiliado.getNombre());
				userActualResponse.setApellido(afiliado.getApellido());
				userActualResponse.setcedulaIdentidad(afiliado.getcedulaIdentidad());
				userActualResponse.setSapId(afiliado.getSapId());
				userActualResponse.setVerificarPerfil(afiliado.getVerificarPerfil());
				userActualResponse.setPermisos(usuario.getRol().getGrantsToString(afiliado));
				
				for(Afiliado afiliadoVinculado : afiliado.getAfiliadosVinculados())
				{
					userVinculadoResponse = new UserResponseDto();
					userVinculadoResponse.setAvatar(afiliadoVinculado.getAvatar());
					userVinculadoResponse.setNombre(afiliadoVinculado.getNombre());
					userVinculadoResponse.setApellido(afiliadoVinculado.getApellido());
					userVinculadoResponse.setcedulaIdentidad(afiliadoVinculado.getcedulaIdentidad());
					userVinculadoResponse.setSapId(afiliadoVinculado.getSapId());
					userVinculadoResponse.setVerificarPerfil(afiliadoVinculado.getVerificarPerfil());
					userVinculadoResponse.setPermisos(usuario.getRol().getGrantsToString(afiliadoVinculado));
					userVinculadoResponseList.add(userVinculadoResponse);
				}

				/* Login */
				switch (deviceType) {
					case "AND":
						if(!afiliado.getLoginAndroid()) {
							this.vincularUsuarioSAP(afiliado.getcedulaIdentidad(), afiliado.getSapId(), deviceType);
							afiliado.setLoginAndroid(true);
							afiliado.updateOnRepository(afiliadoRepository);
						}
						break;
					case "IOS":
						if(!afiliado.getLoginIOS()) {
							this.vincularUsuarioSAP(afiliado.getcedulaIdentidad(), afiliado.getSapId(), deviceType);
							afiliado.setLoginIOS(true);
							afiliado.updateOnRepository(afiliadoRepository);
						}
						break;
					case "WEB":
						if(!afiliado.getLoginWEB()) {
							this.vincularUsuarioSAP(afiliado.getcedulaIdentidad(), afiliado.getSapId(), deviceType);
							afiliado.setLoginWEB(true);
							afiliado.updateOnRepository(afiliadoRepository);
						}
						break;
					default:
						throw new Exception();
				}
			}
			else
			{
				throw new ServiceException("Error al iniciar sesión");
			}
			
			/* Respuesta */
			response.setToken(sesion.getToken());
			response.setRol(sesion.getRol());
			response.setUsuario(userActualResponse);
			response.setOtrosUsuarios(userVinculadoResponseList);
		}
		catch (ServiceException e) {
			throw e;
		}
		catch (Exception e) {
			ScienzaLogger.logError(e);
			throw new ServiceException("Error al iniciar sesión");
		}
		
		return response;
	}


	@Override
	public void logout(String token, Long sapId, String deviceType) throws ServiceException {
		
		try 
		{
			Sesion sesion = sesionRepository.findByToken(token);		
			if(sesion != null)
			{
				sesion.deleteOnRepository(sesionRepository);
			}
		}
		catch (Exception e) {
			ScienzaLogger.logError(e);
			throw new ServiceException("Error al cerrar sesión");
		}	
	}


	/**
	 * Consulta a SAP sobre el afiliado
	 * @param
	 * @throws ServiceException
	 */
	private DTSCIENZAREGUSUARIOUYResponse vincularUsuarioSAP(String cedulaIdentidad, Long sapId, String deviceType) throws Exception {

		DTSCIENZAREGUSUARIOUYResponse remoteResponse = null;
		
		DTSCIENZAREGUSUARIOUY remoteRequest = new DTSCIENZAREGUSUARIOUY();
		remoteRequest.setPACIENTECI(cedulaIdentidad.toString());
		remoteRequest.setPACIENTEID(sapId.toString());
		remoteRequest.setREGISTRACION(deviceType);
		
		remoteResponse = affiliateBindingRemoteService.bindAffiliate(remoteRequest);
		
		if(!SAPResultadoEnum.STATUS_OK.getCodigo().equals(remoteResponse.getRESULTADO()))
			throw new Exception();
		
		return remoteResponse;
	}

}

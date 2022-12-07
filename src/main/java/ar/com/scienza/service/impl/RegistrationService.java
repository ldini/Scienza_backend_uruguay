package ar.com.scienza.service.impl;

import java.io.IOException;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import ar.com.scienza.config.FreeMarkerConfig;
import ar.com.scienza.dto.*;
import ar.com.scienza.entity.*;
import ar.com.scienza.enumerator.TipoEmailEnum;
import ar.com.scienza.exception.*;
import ar.com.scienza.mailsender.MailSender;
import ar.com.scienza.remote.json.dto.AffiliateDataEmailRequestDto;
import ar.com.scienza.remote.ws.dto.DTSCIENZAREGUSUARIOUY;
import ar.com.scienza.remote.ws.dto.DTSCIENZAREGUSUARIOUYResponse;
import ar.com.scienza.repository.*;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.scienza.config.ScienzaLogger;
import ar.com.scienza.enumerator.EstadoBandejaEnum;
import ar.com.scienza.enumerator.RolEnum;
import ar.com.scienza.enumerator.SAPResultadoEnum;
import ar.com.scienza.remote.ws.service.AffiliateBindingRemoteService;
import ar.com.scienza.service.IRegistrationService;
import ar.com.scienza.utils.IntegerUtil;

import javax.mail.MessagingException;


@Service
@Transactional(rollbackFor={ServiceException.class, CustomServiceException.class})
public class RegistrationService implements IRegistrationService {

	@Autowired
	private AfiliadoRepository afiliadoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private RolRepository rolRepository;

	@Autowired
	private SesionRepository sesionRepository;
	
	@Autowired
	private SeguroMedicoRepository seguroMedicoRepository;

	@Autowired
	private CompaniaCelularRepository companiaCelularRepository;

	@Autowired
	private BandejaAltaPendienteRepository bandejaAltaPendienteRepository;

	@Autowired
	private ObservacionAltaPendienteRepository observacionAltaPendienteRepository;

	@Autowired
	private AffiliateBindingRemoteService affiliateBindingRemoteService;

	@Autowired
	private MailSender mailSender;

	@Autowired
	private FreeMarkerConfig freeMarkerConfig;

	@Autowired
	private EmailRepository emailRepository;

	@Value("${mail.environment}")
	String mailEnvironment;
	
	
	private final static String REGISTRATION_ERROR = "Los datos del afiliado no han podido ser validados con SAP. Por favor verifíquelos.";
	private final static String REGISTRATION_PREVIOUS_ERROR = "Ya se encuentra dado de alta. Si olvidó sus datos, ingrese a la opción \"Olvidé mi contraseña\"";
	private final static String PENDING_REGISTRATION_ERROR = "Error al obtener la lista de altas pendientes";
	private final static String SAVE_PENDING_REGISTRATION_ERROR = "Error al guardar el alta pendiente";
	private final static String SCIENZA_COMUNICATION = "Scienza se comunicará con usted a la brevedad.";



	
	@Override
	public RegistrationResponseDto registrate(RegistrationRequestDto request, String token, Long sapId, String deviceType) throws ServiceException, CustomServiceException {

		RegistrationResponseDto response = new RegistrationResponseDto();
		
		Afiliado afiliado = null;
		Sesion sesion = null;
		UserResponseDto userResponse = null;
	
		try 
		{			
			this.validarAfiliadoRegistrado(request);
			
			afiliado = this.newAffiliate(request, deviceType);
			
			sesion = new Sesion();
			sesion.setToken(UUID.randomUUID().toString().toUpperCase());
			sesion.setTipoDispositivo(deviceType);
			sesion.setRol(RolEnum.AFILIADO.getCodigo());
			sesion.setAfiliado(afiliado);
			sesion.createOnRepository(sesionRepository);
			
			userResponse = new UserResponseDto();
			userResponse.setAvatar(afiliado.getAvatar());
			userResponse.setNombre(afiliado.getNombre());
			userResponse.setApellido(afiliado.getApellido());
			userResponse.setcedulaIdentidad(afiliado.getcedulaIdentidad());
			userResponse.setSapId(afiliado.getSapId());
			userResponse.setVerificarPerfil(afiliado.getVerificarPerfil());
			userResponse.setPermisos(afiliado.getUsuario().getRol().getGrantsToString(afiliado));
			
			response.setToken(sesion.getToken());
			response.setRol(sesion.getRol());
			response.setUsuario(userResponse);
			
			for(Afiliado afiliadoVinculado : afiliado.getAfiliadosVinculados())
			{
				userResponse = new UserResponseDto();
				userResponse.setAvatar(afiliadoVinculado.getAvatar());
				userResponse.setNombre(afiliadoVinculado.getNombre());
				userResponse.setApellido(afiliadoVinculado.getApellido());
				userResponse.setcedulaIdentidad(afiliadoVinculado.getcedulaIdentidad());
				userResponse.setSapId(afiliadoVinculado.getSapId());
				userResponse.setVerificarPerfil(afiliadoVinculado.getVerificarPerfil());
				userResponse.setPermisos(afiliadoVinculado.getUsuario().getRol().getGrantsToString(afiliadoVinculado));
				response.getOtrosUsuarios().add(userResponse);
			}
		}
		catch (ServiceException e) {
			throw e;
		}
		catch (Exception e) {
			ScienzaLogger.logError(e);
			throw new CustomServiceException(2, REGISTRATION_ERROR);
		}
		return response;
	}


	@Override
	public RegistrationErrorResponseDto registrateError(RegistrationErrorRequestDto request, String token, Long sapId, String deviceType) throws ServiceException, MessagingException, IOException, TemplateException {

		RegistrationErrorResponseDto response = new RegistrationErrorResponseDto();
		
		try 
		{
			BandejaAltaPendiente bandeja = new BandejaAltaPendiente();
			bandeja.setNombre(request.getNombre());
			bandeja.setApellido(request.getApellido());
			bandeja.setcedulaIdentidad(request.getcedulaIdentidad());
			bandeja.setSapId(request.getSapId());
			bandeja.setPassword(request.getPassword());
			bandeja.setTelefono(request.getTelefono());
			bandeja.setEmail(request.getEmail());
			bandeja.setTipoDispositivo(deviceType);
			bandeja.setEstado(EstadoBandejaEnum.PENDIENTE.getCodigo());
			bandeja.setFechaError(new Date());
			bandeja.createOnRepository(bandejaAltaPendienteRepository);

			response.setMessage(SCIENZA_COMUNICATION);
			sendWelcomeEmail(request);
		}
		catch (Exception e) {

			ScienzaLogger.logError(e);
			throw new ServiceException(REGISTRATION_ERROR);
		}
		
		return response;
	}


	@Override
	public PendingRegistrationsResponseDto getPendingRegistrations(String token, Long sapId, String deviceType) throws ServiceException {

		PendingRegistrationsResponseDto response = new PendingRegistrationsResponseDto();
		
		try 
		{
			List<PendingRegistrationResponseDto> altasPendientes = new ArrayList<>();
			List<BandejaAltaPendiente> bandejaPendientesList = bandejaAltaPendienteRepository.findByEstadoOrderByFechaErrorAsc(EstadoBandejaEnum.PENDIENTE.getCodigo());
			for(BandejaAltaPendiente bandeja : bandejaPendientesList)
			{
				PendingRegistrationResponseDto altaPendiente = new PendingRegistrationResponseDto();
				altaPendiente.setBandejaId(bandeja.getId());
				altaPendiente.setFechaError(new SimpleDateFormat("dd/MM/yyyy HH:mm").format(bandeja.getFechaError()));
				altaPendiente.setNombre(bandeja.getNombre());
				altaPendiente.setApellido(bandeja.getApellido());
				altaPendiente.setcedulaIdentidad(bandeja.getcedulaIdentidad());
				altaPendiente.setSapId(bandeja.getSapId());
				altaPendiente.setTelefono(bandeja.getTelefono());
				altaPendiente.setEmail(bandeja.getEmail());
				altaPendiente.setEstado(EstadoBandejaEnum.PENDIENTE.getDescripcion());
				altasPendientes.add(altaPendiente);
			}
			response.setAltasPendientes(altasPendientes);

			List<PendingRegistrationResponseDto> altasEnCurso = new ArrayList<>();
			List<BandejaAltaPendiente> bandejaEnCursoList = bandejaAltaPendienteRepository.findByEstadoOrderByFechaErrorAsc(EstadoBandejaEnum.EN_CURSO.getCodigo());
			for(BandejaAltaPendiente bandeja : bandejaEnCursoList)
			{
				PendingRegistrationResponseDto altaEnCurso = new PendingRegistrationResponseDto();
				altaEnCurso.setBandejaId(bandeja.getId());
				altaEnCurso.setFechaError(new SimpleDateFormat("dd/MM/yyyy HH:mm").format(bandeja.getFechaError()));
				altaEnCurso.setNombre(bandeja.getNombre());
				altaEnCurso.setApellido(bandeja.getApellido());
				altaEnCurso.setcedulaIdentidad(bandeja.getcedulaIdentidad());
				altaEnCurso.setSapId(bandeja.getSapId());
				altaEnCurso.setTelefono(bandeja.getTelefono());
				altaEnCurso.setEmail(bandeja.getEmail());
				altaEnCurso.setEstado(EstadoBandejaEnum.EN_CURSO.getDescripcion());
				altasEnCurso.add(altaEnCurso);
			}
			response.setAltasEnCurso(altasEnCurso);
		}
		catch (Exception e) {
			ScienzaLogger.logError(e);
			throw new ServiceException(PENDING_REGISTRATION_ERROR);
		}
		
		return response;
	}


	@Override
	public PendingRegistrationResponseDto getPendingRegistration(Integer bandejaId, String token, Long sapId, String deviceType) throws ServiceException {

		PendingRegistrationResponseDto response = new PendingRegistrationResponseDto();
		
		try 
		{
			BandejaAltaPendiente bandeja = bandejaAltaPendienteRepository.findOne(bandejaId);
			response.setBandejaId(bandeja.getId());
			response.setFechaError(new SimpleDateFormat("dd/MM/yyyy HH:mm").format(bandeja.getFechaError()));
			response.setNombre(bandeja.getNombre());
			response.setApellido(bandeja.getApellido());
			response.setcedulaIdentidad(bandeja.getcedulaIdentidad());
			response.setSapId(bandeja.getSapId());
			response.setTelefono(bandeja.getTelefono());
			response.setEmail(bandeja.getEmail());
			if (bandeja.getEstado().equals(EstadoBandejaEnum.PENDIENTE.getCodigo())){
				response.setEstado(EstadoBandejaEnum.PENDIENTE.getDescripcion());
			} else if (bandeja.getEstado().equals(EstadoBandejaEnum.EN_CURSO.getCodigo())){
				response.setEstado((EstadoBandejaEnum.EN_CURSO.getDescripcion()));
			}
			List<ObservacionAltaPendienteResponseDto> observacionesResponse = new ArrayList<>();
			List<ObservacionAltaPendiente> observaciones = observacionAltaPendienteRepository.findAllByBandejaAltaPendienteId(bandejaId);
			for (ObservacionAltaPendiente observacion: observaciones){
				ObservacionAltaPendienteResponseDto observacionResponse = new ObservacionAltaPendienteResponseDto();
				observacionResponse.setTexto(observacion.getTexto());
				Administrador administrador = observacion.getAdministrador();
				String nombreAdministrador = administrador.getNombre();
				String apellidoAdministrador = administrador.getApellido();
				observacionResponse.setNombreAdministrador(nombreAdministrador);
				observacionResponse.setApellidoAdministrador(apellidoAdministrador);
				observacionResponse.setFecha(new SimpleDateFormat("dd/MM/yyyy HH:mm").format(observacion.getFechaInsert()));
				observacionesResponse.add(observacionResponse);
			}
			response.setObservaciones(observacionesResponse);
		}
		catch (Exception e) {
			ScienzaLogger.logError(e);
			throw new ServiceException(PENDING_REGISTRATION_ERROR);
		}
		
		return response;
	}

	@Override
	public void savePendingRegistration(PendingRegistrationSaveRequestDto request, String token, Long sapId, String deviceType) throws ServiceException{

		try
		{
			BandejaAltaPendiente bandeja = bandejaAltaPendienteRepository.findOne(request.getBandejaId());
			Administrador administrador = sesionRepository.findByToken(token).getAdministrador();

			ObservacionAltaPendiente observacion = new ObservacionAltaPendiente();
			observacion.setBandejaAltaPendiente(bandeja);
			observacion.setAdministrador(administrador);
			observacion.setTexto(request.getObservacion());
			observacion.createOnRepository(observacionAltaPendienteRepository);

			bandeja.setEstado(EstadoBandejaEnum.EN_CURSO.getCodigo());
			bandeja.setcedulaIdentidad(request.getcedulaIdentidad());
			bandeja.setSapId(request.getSapId());
			bandeja.updateOnRepository(bandejaAltaPendienteRepository);

		}catch(Exception e){
			ScienzaLogger.logError(e);
			throw new ServiceException(SAVE_PENDING_REGISTRATION_ERROR);
		}
	}


	@Override
	public void bindPendingRegistration(PendingRegistrationBindRequestDto request, String token, Long sapId, String deviceType) throws ServiceException {

		try 
		{			
			BandejaAltaPendiente bandeja = bandejaAltaPendienteRepository.findOne(request.getBandejaId());
			
			Administrador administrador = sesionRepository.findByToken(token).getAdministrador();
			
			RegistrationRequestDto regRequest = new RegistrationRequestDto();
			regRequest.setcedulaIdentidad(request.getcedulaIdentidad());
			regRequest.setSapId(request.getSapId());
			regRequest.setPassword(bandeja.getPassword());
			this.newAffiliate(regRequest, bandeja.getTipoDispositivo());
			
			bandeja.setEstado(EstadoBandejaEnum.APROBADO.getCodigo());
			bandeja.setFechaAtencion(new Date());
			bandeja.setAdministrador(administrador);
			bandeja.updateOnRepository(bandejaAltaPendienteRepository);
		}
		catch (AffiliateHealthInsuranceNotFoundException | AffiliateCellphoneCompanyNotFoundException |
				InvalidAffiliateBirthDateException | InvalidAffiliateSexException |
				InvalidAffiliateStreetNumberException e) {
			ScienzaLogger.logError(e);
			throw new ServiceException(e.getMessage());
		}
		catch (Exception e) {
			ScienzaLogger.logError(e);
			throw new ServiceException(REGISTRATION_ERROR);
		}
	}


	@Override
	public void rejectPendingRegistration(PendingRegistrationRejectRequestDto request, String token, Long sapId, String deviceType) throws ServiceException {

		try 
		{			
			BandejaAltaPendiente bandeja = bandejaAltaPendienteRepository.findOne(request.getBandejaId());
			
			Administrador administrador = sesionRepository.findByToken(token).getAdministrador();
			
			bandeja.setEstado(EstadoBandejaEnum.RECHAZADO.getCodigo());
			bandeja.setFechaAtencion(new Date());
			bandeja.setAdministrador(administrador);
			bandeja.updateOnRepository(bandejaAltaPendienteRepository);
		}
		catch (Exception e) {
			ScienzaLogger.logError(e);
			throw new ServiceException(REGISTRATION_ERROR);
		}
	}


	/**
	 * Crea un nuevo afiliado en el sistema
	 * @param request
	 * @return
	 * @throws ServiceException
	 */
	private Afiliado newAffiliate(RegistrationRequestDto request, String deviceType) throws AffiliateHealthInsuranceNotFoundException,Exception  {
		
		Afiliado afiliado = afiliadoRepository.findBycedulaIdentidadAndSapId(request.getcedulaIdentidad(), request.getSapId());
		
		if(afiliado == null || afiliado.getUsuario() == null)
		{
			DTSCIENZAREGUSUARIOUYResponse remoteResponse = this.vincularUsuarioSAP(request, deviceType);
			
			Rol rol = rolRepository.findByNombre(RolEnum.AFILIADO.getCodigo());
			
			Usuario usuario = new Usuario();
			usuario.setUserName(request.getcedulaIdentidad().toString());
			usuario.setPassword(request.getPassword());
			usuario.setEnabled(true);
			usuario.setRol(rol);
			usuario.createOnRepository(usuarioRepository);

			if(afiliado == null) {
				afiliado = new Afiliado();
				afiliado.setSapId(request.getSapId());
				afiliado.setNombre(remoteResponse.getPACIENTENOMBRE());
				afiliado.setApellido(remoteResponse.getPACIENTEAPELLIDO());
				SeguroMedico seguroMedico = seguroMedicoRepository.findBySapId(new Long(remoteResponse.getPACIENTEIMID()));
				afiliado.setSeguroMedico(seguroMedico);
				afiliado.setNumeroAfiliado(remoteResponse.getPACIENTEIMID());
				afiliado.setcedulaIdentidad(request.getcedulaIdentidad());

				if (remoteResponse.getPACIENTESEXO() != null) 
				{
					String sexo = String.valueOf(remoteResponse.getPACIENTESEXO().charAt(0));

					if (Arrays.asList("M", "F", "I").contains(sexo.toUpperCase())) {
							afiliado.setSexo(sexo.toUpperCase());
						}
					else {
							throw new InvalidAffiliateSexException();
						}
					}
				else {
						afiliado.setSexo("I");
				}
				
				if (remoteResponse.getPACIENTEFECHANAC() != null && !remoteResponse.getPACIENTEFECHANAC().equals("00000000")) {
					try {
						afiliado.setFechaNacimiento(new SimpleDateFormat("yyyyMMdd").parse(remoteResponse.getPACIENTEFECHANAC()));
					} catch (ParseException ex) {
						throw new InvalidAffiliateBirthDateException();
					}
				}
				else {
					afiliado.setFechaNacimiento(null);
				}

				afiliado.setDepartamento(remoteResponse.getPACIENTEAPARTAMENTO());
				afiliado.setLocalidad(remoteResponse.getPACIENTELOCALIDAD());
				afiliado.setCalle(remoteResponse.getPACIENTECALLE());

				if (remoteResponse.getPACIENTENUMERO() != null) {
					try 
					{
						afiliado.setCalleNumero(IntegerUtil.valueOf(remoteResponse.getPACIENTENUMERO().replaceAll("[^0-9]", "")));
					}
					catch (ParseException ex) {
						throw new InvalidAffiliateStreetNumberException();
					}
				}

				afiliado.setPiso(remoteResponse.getPACIENTEPISO());
				afiliado.setProvincia(remoteResponse.getPACIENTEDEPARTAMENTO());
				afiliado.setCodigoPostal(remoteResponse.getPACIENTECP());
				afiliado.setTelefonoParticular(remoteResponse.getPACIENTETELPARTICULAR());
				afiliado.setTelefonoLaboral(remoteResponse.getPACIENTETELLABORAL());
				afiliado.setTelefonoCelular(remoteResponse.getPACIENTETELCELULAR());

				CompaniaCelular companiaCelular = companiaCelularRepository.findByDescripcionIgnoreCase(remoteResponse.getPACIENTECOMPCELULAR());
				if (companiaCelular != null){
					afiliado.setCompaniaCelular(companiaCelular);
				}

				afiliado.setEmail(remoteResponse.getPACIENTEEMAIL());
				afiliado.setVerificarPerfil(true);
				afiliado.setLoginAndroid(false);
				afiliado.setLoginIOS(false);
				afiliado.setLoginWEB(false);
				afiliado.setUsuario(usuario);
			}
			else if(afiliado.getUsuario() == null) {
				afiliado.setUsuario(usuario);
			}
			
			/* Login */
			switch (deviceType) {
				case "AND":
					afiliado.setLoginAndroid(true);
					break;
				case "IOS":
					afiliado.setLoginIOS(true);
					break;
				case "WEB":
					afiliado.setLoginWEB(true);
					break;
				default:
					throw new Exception();
			}
			
			/* Normalizacion */
//			try
//			{
//				String address = afiliado.getCalle() + " " + afiliado.getCalleNumero() + ", " + afiliado.getLocalidad() + ", " + afiliado.getProvincia() + ", Argentina";
//				GoogleMapsFullDataResponseDto gmResponse = googleMapsService.getFullData(address, null, null, null);
//				if(gmResponse != null)
//				{
//					afiliado.setDepartamento(gmResponse.getProvincia());
//					afiliado.setLocalidad(gmResponse.getLocalidad());
//					afiliado.setCalle(gmResponse.getCalle());
//					afiliado.setCalleNumero(gmResponse.getCalleNumero() != null ? gmResponse.getCalleNumero() : afiliado.getCalleNumero());
//					afiliado.setCodigoPostal(gmResponse.getCodigoPostal() != null ? StringUtil.stripNonDigits(gmResponse.getCodigoPostal()) : afiliado.getCodigoPostal());
//					afiliado.setLatitud(gmResponse.getLatitud());
//					afiliado.setLongitud(gmResponse.getLongitud());
//				}
//			}
//			catch(ServiceException e) {
//				Exception e2 = new Exception(e.getMessage());
//				e2.setStackTrace(e.getStackTrace());
//				throw e2;
//			}
			
			afiliado.createOnRepository(afiliadoRepository);
		}
		
		return afiliado;
	}


	/**
	 * Consulta a SAP sobre el afiliado
	 * @param request
	 * @throws ServiceException
	 */
	private DTSCIENZAREGUSUARIOUYResponse vincularUsuarioSAP(RegistrationRequestDto request, String deviceType) throws Exception {

		DTSCIENZAREGUSUARIOUYResponse remoteResponse = null;
		
		DTSCIENZAREGUSUARIOUY remoteRequest = new DTSCIENZAREGUSUARIOUY();
		remoteRequest.setPACIENTECI(request.getcedulaIdentidad());
		remoteRequest.setPACIENTEID(request.getSapId().toString());
		remoteRequest.setREGISTRACION(deviceType);
		
		remoteResponse = affiliateBindingRemoteService.bindAffiliate(remoteRequest);
		
		if(!SAPResultadoEnum.STATUS_OK.getCodigo().equals(remoteResponse.getRESULTADO()))
			throw new Exception();
		
		return remoteResponse;
	}


	/**
	 * Verifica si una persona ya se dio de alta
	 * @param request
	 * @throws ServiceException 
	 */
	private void validarAfiliadoRegistrado(RegistrationRequestDto request) throws ServiceException {

		Afiliado afiliado = afiliadoRepository.findBycedulaIdentidadAndSapId(request.getcedulaIdentidad(), request.getSapId());
		if(afiliado!= null && afiliado.getUsuario() != null)
			throw new ServiceException(REGISTRATION_PREVIOUS_ERROR);
	}

	private void sendWelcomeEmail(RegistrationErrorRequestDto afiliado) throws IOException, MessagingException, TemplateException {

		Map<Object, Object> root = new HashMap<Object, Object>();

		AffiliateDataEmailRequestDto emailRequestDto = new AffiliateDataEmailRequestDto();

		Date dateRegistration = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm 'hs'");

		emailRequestDto.setCaseType("Solicitud de gestión en la app");
		emailRequestDto.setCategory("Error en vinculación de paciente");
		emailRequestDto.setName(afiliado.getApellido() + " , " + afiliado.getNombre());
		emailRequestDto.setDateCase(sdf.format(dateRegistration));
		emailRequestDto.setSapId(afiliado.getSapId().toString());
		emailRequestDto.setTelephone(afiliado.getTelefono());

		if( afiliado.getEmail() != null){

			emailRequestDto.setEmail(afiliado.getEmail());
		}
		else {

			emailRequestDto.setEmail("");
		}


		emailRequestDto.setUrlToOpen(mailEnvironment);

		root.put("titulo", emailRequestDto.getCaseType());
		root.put("categoria", emailRequestDto.getCategory());
		root.put("fechacreacion",emailRequestDto.getDateCase());
		root.put("nombreCompleto", emailRequestDto.getName());
		root.put("sapId", emailRequestDto.getSapId());
		root.put("telephone", emailRequestDto.getTelephone());
		root.put("email", emailRequestDto.getEmail());
		root.put("url",emailRequestDto.getUrlToOpen());


		Template template = freeMarkerConfig.getCfg().getTemplate("html/email-error-vinculacion.html");
		StringWriter stringWriter = new StringWriter();
		template.process(root, stringWriter);

		String message = stringWriter.toString();

		Email email = this.emailRepository.findByTipoEmail(TipoEmailEnum.ERROR_VINCULACION.getCodigo());

		mailSender.sendMail(email, email.getSubject(), message, null);

	}

}

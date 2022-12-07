package ar.com.scienza.service.impl;

import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.*;

import ar.com.scienza.config.FreeMarkerConfig;
import ar.com.scienza.dto.*;
import ar.com.scienza.entity.Email;
import ar.com.scienza.enumerator.TipoEmailEnum;
import ar.com.scienza.mailsender.MailSender;
import ar.com.scienza.remote.json.dto.AffiliateDataEmailRequestDto;
import ar.com.scienza.repository.*;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.scienza.config.ScienzaLogger;
import ar.com.scienza.entity.Administrador;
import ar.com.scienza.entity.Afiliado;
import ar.com.scienza.entity.BandejaPerfilPendiente;
import ar.com.scienza.enumerator.EstadoBandejaEnum;
import ar.com.scienza.enumerator.TipoNotificacionEnum;
import ar.com.scienza.exception.ServiceException;
import ar.com.scienza.service.INotificationService;
import ar.com.scienza.service.IProfileService;

import javax.mail.MessagingException;


@Service
@Transactional(rollbackFor=ServiceException.class)
public class ProfileService implements IProfileService {

	@Autowired
	private AfiliadoRepository afiliadoRepository;
	
	@Autowired
	private SesionRepository sesionRepository;

	@Autowired
	private CompaniaCelularRepository companiaCelularRepository;

	@Autowired
	private BandejaPerfilPendienteRepository bandejaPerfilPendienteRepository;

	@Autowired
	private INotificationService notificationService;

	@Autowired
	private MailSender mailSender;

	@Autowired
	private FreeMarkerConfig freeMarkerConfig;

	@Autowired
	private EmailRepository emailRepository;

	@Value("${mail.environment}")
	String mailEnvironment;


	@Override
	public AffiliateProfileResponseDto getAffiliateProfile(String token, Long sapId, String deviceType) throws ServiceException {

		AffiliateProfileResponseDto response = null;
	
		try 
		{
			Afiliado afiliado = sesionRepository.findByToken(token).getAfiliado().getAfiliadoActivo(sapId);
			if(afiliado == null) {
				throw new Exception();
			}
			
			Long countBandejaPendiente = bandejaPerfilPendienteRepository.countByEstadoAndAfiliadoId(EstadoBandejaEnum.PENDIENTE.getCodigo(), afiliado.getId());
			
			response = new AffiliateProfileResponseDto();
			response.setPendienteVerificacion(countBandejaPendiente.compareTo(new Long(0)) != 0);
			response.setNombre(afiliado.getNombre());
			response.setApellido(afiliado.getApellido());
			response.setApodo(afiliado.getApodo());
			response.setSeguroMedico(afiliado.getSeguroMedico().getDescripcion());
			response.setcedulaIdentidad(afiliado.getcedulaIdentidad());
			response.setSexo(afiliado.getSexo());

			if (afiliado.getFechaNacimiento() != null) {
				response.setFechaNacimiento(new SimpleDateFormat("dd/MM/yyyy").format(afiliado.getFechaNacimiento()));
			}
			else
			{
				response.setFechaNacimiento(null);
			}

			response.setProvincia(afiliado.getProvincia());
			response.setLocalidad(afiliado.getLocalidad());
			response.setCalle(afiliado.getCalle());
			response.setCalleNumero(afiliado.getCalleNumero());
			response.setPiso(afiliado.getPiso());
			response.setDepartamento(afiliado.getDepartamento());
			response.setCodigoPostal(afiliado.getCodigoPostal());
			response.setTelefonoParticular(afiliado.getTelefonoParticular());
			response.setTelefonoLaboral(afiliado.getTelefonoLaboral());
			response.setTelefonoCelular(afiliado.getTelefonoCelular());
			response.setCodigoCompaniaCelular((afiliado.getCompaniaCelular() != null) ? afiliado.getCompaniaCelular().getCodigo() : null);
			response.setEmail(afiliado.getEmail());
		}
		catch (Exception e) {
			ScienzaLogger.logError(e);
			throw new ServiceException("Error al obtener perfil del paciente");
		}
		
		return response;
	}


	@Override
	public AffiliateProfileEditResponseDto editAffiliateProfile(AffiliateProfileEditRequestDto request, String token, Long sapId, String deviceType) throws ServiceException {

		AffiliateProfileEditResponseDto response = new AffiliateProfileEditResponseDto();
		
		try 
		{
			Afiliado afiliado = sesionRepository.findByToken(token).getAfiliado().getAfiliadoActivo(sapId);
			if(afiliado == null) {
				throw new Exception();
			}

			Long countBandejaPendiente = bandejaPerfilPendienteRepository.countByEstadoAndAfiliadoId(EstadoBandejaEnum.PENDIENTE.getCodigo(), afiliado.getId());
			if(countBandejaPendiente.compareTo(new Long(0)) != 0)
				throw new ServiceException("El perfil esta pendiente de verificación");
			
			
			if(afiliado.getVerificarPerfil() || !afiliado.equals(request)) 
			{
				if(afiliado.getVerificarPerfil()) {
					afiliado.setVerificarPerfil(false);
					afiliado.updateOnRepository(afiliadoRepository);
				}
				
				BandejaPerfilPendiente bandeja = new BandejaPerfilPendiente();
				bandeja.setAfiliado(afiliado);
				bandeja.setNombre(request.getNombre());
				bandeja.setApellido(request.getApellido());
				bandeja.setSexo(request.getSexo());
				if (request.getFechaNacimiento() != null) {
					bandeja.setFechaNacimiento(new SimpleDateFormat("dd/MM/yyyy").parse(request.getFechaNacimiento()));
				}
				else {
					bandeja.setFechaNacimiento(null);
				}
				bandeja.setProvincia(request.getProvincia());
				bandeja.setLocalidad(request.getLocalidad());
				bandeja.setCalle(request.getCalle());
				bandeja.setCalleNumero(request.getCalleNumero());
				bandeja.setPiso(request.getPiso());
				bandeja.setDepartamento(request.getDepartamento());
				bandeja.setCodigoPostal(request.getCodigoPostal());
				bandeja.setTelefonoParticular(request.getTelefonoParticular());
				bandeja.setTelefonoLaboral(request.getTelefonoLaboral());
				bandeja.setTelefonoCelular(request.getTelefonoCelular());
				bandeja.setCompaniaCelular((request.getCodigoCompaniaCelular() != null) ? companiaCelularRepository.findByCodigo(request.getCodigoCompaniaCelular()) : null);
				bandeja.setEmail(request.getEmail());
				bandeja.setTipoDispositivo(deviceType);
				bandeja.setEstado(EstadoBandejaEnum.PENDIENTE.getCodigo());
				bandeja.setFechaEdicion(new Date());

				bandeja.createOnRepository(bandejaPerfilPendienteRepository);
				
				response.setPendienteVerificacion(true);
				response.setMessage("Sus datos serán verificados a la brevedad. Muchas gracias.");
				sendAffiliateChangeEmail(bandeja);
			}		
		}
		catch (ServiceException e) {
			throw e;
		}
		catch (Exception e) {
			ScienzaLogger.logError(e);
			throw new ServiceException("Error al procesar perfil del paciente");
		}
		
		return response;
	}


	@Override
	public List<PendingProfileResultResponseDto> getPendingProfileList(String token, Long sapId, String deviceType) throws ServiceException {

		List<PendingProfileResultResponseDto> responseList = new ArrayList<PendingProfileResultResponseDto>();
		
		try 
		{
			List<BandejaPerfilPendiente> bandejaList = bandejaPerfilPendienteRepository.findByEstadoOrderByFechaEdicionAsc(EstadoBandejaEnum.PENDIENTE.getCodigo());
			for(BandejaPerfilPendiente bandeja : bandejaList)
			{
				PendingProfileResultResponseDto response = new PendingProfileResultResponseDto();
				response.setBandejaId(bandeja.getId());
				response.setFechaEdicion(new SimpleDateFormat("dd/MM/yyyy HH:mm").format(bandeja.getFechaEdicion()));
				response.setNombre(bandeja.getNombre());
				response.setApellido(bandeja.getApellido());
				response.setcedulaIdentidad(bandeja.getAfiliado().getcedulaIdentidad());
				response.setSapId(bandeja.getAfiliado().getSapId());
				response.setSeguroMedico(bandeja.getAfiliado().getSeguroMedico().getDescripcion());
				responseList.add(response);
			}
		}
		catch (Exception e) {
			ScienzaLogger.logError(e);
			throw new ServiceException("Error al obtener perfiles pendientes");
		}
		
		return responseList;
	}


	@Override
	public PendingProfileResponseDto getPendingProfile(Integer bandejaId, String token, Long sapId, String deviceType) throws ServiceException {

		PendingProfileResponseDto response = new PendingProfileResponseDto();
		
		try 
		{
			BandejaPerfilPendiente bandeja = bandejaPerfilPendienteRepository.findOne(bandejaId);
			response.setBandejaId(bandeja.getId());
			response.setSapId(bandeja.getAfiliado().getSapId());
			response.setNombre(bandeja.getNombre());
			response.setApellido(bandeja.getApellido());
			response.setApodo(bandeja.getApodo());
			response.setSeguroMedico(bandeja.getAfiliado().getSeguroMedico().getDescripcion());
			response.setNumeroAfiliado(bandeja.getAfiliado().getNumeroAfiliado());
			response.setcedulaIdentidad(bandeja.getAfiliado().getcedulaIdentidad());
			response.setSexo(bandeja.getSexo());
			if (bandeja.getFechaNacimiento() != null) {
				response.setFechaNacimiento(new SimpleDateFormat("dd/MM/yyyy").format(bandeja.getFechaNacimiento()));
			}
			else {
				response.setFechaNacimiento(null);
			}
			response.setProvincia(bandeja.getProvincia());
			response.setLocalidad(bandeja.getLocalidad());
			response.setCalle(bandeja.getCalle());
			response.setCalleNumero(bandeja.getCalleNumero());
			response.setPiso(bandeja.getPiso());
			response.setDepartamento(bandeja.getDepartamento());
			response.setCodigoPostal(bandeja.getCodigoPostal());
			response.setLatitud(bandeja.getLatitud());
			response.setLongitud(bandeja.getLongitud());
			response.setTelefonoParticular(bandeja.getTelefonoParticular());
			response.setTelefonoLaboral(bandeja.getTelefonoLaboral());
			response.setTelefonoCelular(bandeja.getTelefonoCelular());
			response.setCompaniaCelular((bandeja.getCompaniaCelular() != null) ? bandeja.getCompaniaCelular().getDescripcion() : null);
			response.setEmail(bandeja.getEmail());
			
			Afiliado afiliado = bandeja.getAfiliado();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			
			/* Propiedades modificadas */
			this.compareProperty(response, afiliado.getNombre(), bandeja.getNombre(), "firstName");
			this.compareProperty(response, afiliado.getApellido(), bandeja.getApellido(), "lastName");
			this.compareProperty(response, afiliado.getApodo(), bandeja.getApodo(), "acronym");
			this.compareProperty(response, afiliado.getSexo(), bandeja.getSexo(), "gender");

			this.compareProperty(
					response,
					(afiliado.getFechaNacimiento() != null) ? afiliado.getFechaNacimiento().toString() : null,
					(bandeja.getFechaNacimiento() != null) ? bandeja.getFechaNacimiento().toString() : null,
					"birthdate"
			);


			this.compareProperty(response, afiliado.getProvincia(), bandeja.getProvincia(), "province");
			this.compareProperty(response, afiliado.getLocalidad(), bandeja.getLocalidad(), "locality");
			this.compareProperty(response, afiliado.getCalle(), bandeja.getCalle(), "street");
			this.compareProperty(
				response, 
				(afiliado.getCalleNumero() != null) ? afiliado.getCalleNumero().toString() : null, 
				(bandeja.getCalleNumero() != null) ? bandeja.getCalleNumero().toString() : null, 
				"streetNumber"
			);
			this.compareProperty(response, afiliado.getPiso(), bandeja.getPiso(), "floor");
			this.compareProperty(response, afiliado.getDepartamento(), bandeja.getDepartamento(), "department");
			this.compareProperty(response, afiliado.getCodigoPostal(), bandeja.getCodigoPostal(), "postalCode");
			this.compareProperty(response, afiliado.getTelefonoParticular(), bandeja.getTelefonoParticular(), "personalPhone");
			this.compareProperty(response, afiliado.getTelefonoLaboral(), bandeja.getTelefonoLaboral(), "workPhone");
			this.compareProperty(response, afiliado.getTelefonoCelular(), bandeja.getTelefonoCelular(), "cellPhone");
			this.compareProperty(
				response, 
				(afiliado.getCompaniaCelular() != null) ? afiliado.getCompaniaCelular().getCodigo() : null, 
				(bandeja.getCompaniaCelular() != null) ? bandeja.getCompaniaCelular().getCodigo() : null, 
				"cellCompany"
			);
			this.compareProperty(response, afiliado.getEmail(), bandeja.getEmail(), "email");	
		}
		catch (Exception e) {
			ScienzaLogger.logError(e);
			throw new ServiceException("Error al obtener perfil pendiente");
		}
		
		return response;
	}


	@Override
	public void acceptPendingProfile(PendingProfileAcceptRequestDto request, String token, Long sapId, String deviceType) throws ServiceException {

		try 
		{			
			BandejaPerfilPendiente bandeja = bandejaPerfilPendienteRepository.findOne(request.getBandejaId());
						
			Administrador administrador = sesionRepository.findByToken(token).getAdministrador();
			
			Afiliado afiliado = bandeja.getAfiliado();
			afiliado.setNombre(bandeja.getNombre());
			afiliado.setApellido(bandeja.getApellido());
			afiliado.setApodo(bandeja.getApodo());
			afiliado.setSexo(bandeja.getSexo());
			afiliado.setFechaNacimiento(bandeja.getFechaNacimiento());
			afiliado.setProvincia(bandeja.getProvincia());
			afiliado.setLocalidad(bandeja.getLocalidad());
			afiliado.setCalle(bandeja.getCalle());
			afiliado.setCalleNumero(bandeja.getCalleNumero());
			afiliado.setPiso(bandeja.getPiso());
			afiliado.setDepartamento(bandeja.getDepartamento());
			afiliado.setCodigoPostal(bandeja.getCodigoPostal());
			afiliado.setLatitud(bandeja.getLatitud());
			afiliado.setLongitud(bandeja.getLongitud());
			afiliado.setTelefonoParticular(bandeja.getTelefonoParticular());
			afiliado.setTelefonoLaboral(bandeja.getTelefonoLaboral());
			afiliado.setTelefonoCelular(bandeja.getTelefonoCelular());
			afiliado.setCompaniaCelular((bandeja.getCompaniaCelular() != null) ? bandeja.getCompaniaCelular() : null);
			afiliado.setEmail(bandeja.getEmail());
			afiliado.updateOnRepository(afiliadoRepository);
			
			bandeja.setEstado(EstadoBandejaEnum.APROBADO.getCodigo());
			bandeja.setFechaAtencion(new Date());
			bandeja.setAdministrador(administrador);
			bandeja.updateOnRepository(bandejaPerfilPendienteRepository);
			
			notificationService.createNotification(
				administrador,
				Arrays.asList(afiliado), 
				"Validación de Perfil", 
				"${afiliado.nombre}, su perfil ha sido validado exitosamente.",
				"Su perfil ha sido validado exitosamente. Por favor, mantenga su perfil actualizado. Muchas gracias.",
				TipoNotificacionEnum.PERFIL_ACEPTADO,
				null
			);
		}
		catch (Exception e) {
			ScienzaLogger.logError(e);
			throw new ServiceException("Error al aceptar perfil pendiente");
		}
	}


	@Override
	public void rejectPendingProfile(PendingProfileRejectRequestDto request, String token, Long sapId, String deviceType) throws ServiceException {

		try 
		{			
			BandejaPerfilPendiente bandeja = bandejaPerfilPendienteRepository.findOne(request.getBandejaId());
			
			Administrador administrador = sesionRepository.findByToken(token).getAdministrador();
			Afiliado afiliado = bandeja.getAfiliado();
			
			bandeja.setEstado(EstadoBandejaEnum.RECHAZADO.getCodigo());
			bandeja.setFechaAtencion(new Date());
			bandeja.setAdministrador(administrador);
			bandeja.updateOnRepository(bandejaPerfilPendienteRepository);

			notificationService.createNotification(
				administrador,
				Arrays.asList(afiliado), 
				"Validación de Perfil",
				"${afiliado.nombre}, sus datos han sido rechazados. Comuníquese con nosotros.",
				"Scienza informa que sus datos han sido rechazados. Comuníquese para mayor información.",
				TipoNotificacionEnum.PERFIL_RECHAZADO,
				null
			);
		}
		catch (Exception e) {
			ScienzaLogger.logError(e);
			throw new ServiceException("Error al rechazar perfil pendiente");
		}
	}
	
	
	/**
	 * Normaliza la direccion ingresada por el afiliado
	 * @param bandeja
	 * @throws ServiceException
	 */
//	private void normalizeAddress(BandejaPerfilPendiente bandeja) throws ServiceException {
//		
//		String address = bandeja.getCalle() + " " + bandeja.getCalleNumero() + ", " + bandeja.getLocalidad() + ", " + bandeja.getProvincia() + ", Argentina";
//		GoogleMapsFullDataResponseDto gmResponse = googleMapsService.getFullData(address, null, null, null);
//		if(gmResponse == null) {
//			throw new ServiceException("Dirección inexistente. Por favor, verifique sus datos");
//		}
//		
//		bandeja.setDepartamento(gmResponse.getProvincia());
//		bandeja.setLocalidad(gmResponse.getLocalidad());
//		bandeja.setCalle(gmResponse.getCalle());
//		bandeja.setCalleNumero(gmResponse.getCalleNumero() != null ? gmResponse.getCalleNumero() : bandeja.getCalleNumero());
//		bandeja.setCodigoPostal(gmResponse.getCodigoPostal() != null ? StringUtil.stripNonDigits(gmResponse.getCodigoPostal()) : bandeja.getCodigoPostal());
//		bandeja.setLatitud(gmResponse.getLatitud());
//		bandeja.setLongitud(gmResponse.getLongitud());
//	}
	
	
	/**
	 * Compara 2 campos. Si son distintos lo agrega a la lista de propiedades modificadas
	 * @param response
	 * @param value1
	 * @param value2
	 * @param property
	 */
	private void compareProperty(PendingProfileResponseDto response, String value1, String value2, String property) {
		
		if(Objects.toString(value1, "").compareTo(Objects.toString(value2, "")) != 0)
			response.getPropiedadModificadaList().add(property);
	}

	
	private void sendAffiliateChangeEmail(BandejaPerfilPendiente pendiente) throws IOException, MessagingException, TemplateException {

		Map<Object, Object> root = new HashMap<Object, Object>();

		AffiliateDataEmailRequestDto emailRequestDto = new AffiliateDataEmailRequestDto();

		Date dateVinculacion = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm 'hs'");

		emailRequestDto.setCaseType("Solicitud de gestión en la app");
		emailRequestDto.setCategory("Validación de datos de paciente");
		emailRequestDto.setName(pendiente.getApellido() + " , " + pendiente.getNombre());
		emailRequestDto.setDateCase(sdf.format(dateVinculacion));
		emailRequestDto.setSapId(pendiente.getAfiliado().getSapId().toString());
		emailRequestDto.setIdentification(pendiente.getAfiliado().getcedulaIdentidad());

		if (pendiente.getTelefonoCelular() != null){

			emailRequestDto.setCellphone(pendiente.getTelefonoCelular());

		}
		else

			emailRequestDto.setCellphone("");

		if(pendiente.getTelefonoParticular() != null){

			emailRequestDto.setTelephone(pendiente.getTelefonoParticular());
		}
		else
			if(pendiente.getTelefonoLaboral() != null) {

				emailRequestDto.setTelephone(pendiente.getTelefonoLaboral());
			}

			else

				emailRequestDto.setTelephone("");

		if(pendiente.getEmail() != null){

			emailRequestDto.setEmail(pendiente.getEmail());
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
		root.put("cedulaIdentidad", emailRequestDto.getIdentification());
		root.put("cellPhone", emailRequestDto.getCellphone());
		root.put("telephone", emailRequestDto.getTelephone());
		root.put("email", emailRequestDto.getEmail());
		root.put("url",emailRequestDto.getUrlToOpen());

		Template template = freeMarkerConfig.getCfg().getTemplate("html/email-validacion-datos.html");
		StringWriter stringWriter = new StringWriter();
		template.process(root, stringWriter);

		String message = stringWriter.toString();

		Email email = this.emailRepository.findByTipoEmail(TipoEmailEnum.DATOS_VALIDACION.getCodigo());

		mailSender.sendMail(email, email.getSubject(), message, null);

	}
}

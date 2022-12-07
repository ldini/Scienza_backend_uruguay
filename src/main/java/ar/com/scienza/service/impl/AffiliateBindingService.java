package ar.com.scienza.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.scienza.config.ScienzaLogger;
import ar.com.scienza.dto.AffiliateBindingRequestDto;
import ar.com.scienza.dto.AffiliateBindingResponseDto;
import ar.com.scienza.dto.AffiliateUnbindingRequestDto;
import ar.com.scienza.dto.AffiliateUnbindingResponseDto;
import ar.com.scienza.dto.UserResponseDto;
import ar.com.scienza.entity.Afiliado;
import ar.com.scienza.entity.CompaniaCelular;
import ar.com.scienza.entity.SeguroMedico;
import ar.com.scienza.entity.Sesion;
import ar.com.scienza.enumerator.SAPResultadoEnum;
import ar.com.scienza.exception.InvalidAffiliateBirthDateException;
import ar.com.scienza.exception.InvalidAffiliateSexException;
import ar.com.scienza.exception.InvalidAffiliateStreetNumberException;
import ar.com.scienza.exception.ServiceException;
import ar.com.scienza.remote.ws.dto.DTSCIENZAREGUSUARIOUY;
import ar.com.scienza.remote.ws.dto.DTSCIENZAREGUSUARIOUYResponse;
import ar.com.scienza.remote.ws.service.AffiliateBindingRemoteService;
import ar.com.scienza.repository.AfiliadoRepository;
import ar.com.scienza.repository.CompaniaCelularRepository;
import ar.com.scienza.repository.SeguroMedicoRepository;
import ar.com.scienza.repository.SesionRepository;
import ar.com.scienza.service.IAffiliateBindingService;
import ar.com.scienza.utils.IntegerUtil;


@Service
@Transactional(rollbackFor=ServiceException.class)
public class AffiliateBindingService implements IAffiliateBindingService {

	@Autowired
	private AfiliadoRepository afiliadoRepository;

	@Autowired
	private SesionRepository sesionRepository;
	
	@Autowired
	private SeguroMedicoRepository seguroMedicoRepository;

	@Autowired
	private CompaniaCelularRepository companiaCelularRepository;

	@Autowired
	private AffiliateBindingRemoteService affiliateBindingRemoteService;

	
	private final static String VINCULATION_ERROR = "Error de vinculación. Por favor, cierre su sesión e ingrese a la opción \"REGISTRARSE\". Allí podrá dar de alta al paciente que desea vincular.";
	private final static String DESVINCULATION_ERROR = "Error en la desvinculación del afiliado";
	

	@Override
	public UserResponseDto bindAffiliate(AffiliateBindingRequestDto request, String token, Long sapId, String deviceType) throws ServiceException {

		UserResponseDto response = null;
	
		try 
		{
			Sesion sesion = sesionRepository.findByToken(token);
			Afiliado afiliado = sesion.getAfiliado();
			Afiliado afiliadoVinculado = this.newAffiliate(request, deviceType);
			
			if(afiliadoVinculado.getId().compareTo(afiliado.getId()) == 0) {
				throw new ServiceException("El afiliado no puede auto-vincularse");
			}
			for(Afiliado afiliadoAux : afiliado.getAfiliadosVinculados()) {
				if(afiliadoVinculado.getSapId().compareTo(afiliadoAux.getSapId()) == 0)
					throw new ServiceException("El afiliado ya se encuentra vinculado");
			}
			
			afiliado.getAfiliadosVinculados().add(afiliadoVinculado);
			afiliado.updateOnRepository(afiliadoRepository);
			
			response = new UserResponseDto();
			response.setAvatar(afiliadoVinculado.getAvatar());
			response.setNombre(afiliadoVinculado.getNombre());
			response.setApellido(afiliadoVinculado.getApellido());
			response.setcedulaIdentidad(afiliadoVinculado.getcedulaIdentidad());
			response.setSapId(afiliadoVinculado.getSapId());
			response.setVerificarPerfil(afiliadoVinculado.getVerificarPerfil());
			response.setPermisos(afiliado.getUsuario().getRol().getGrantsToString(afiliadoVinculado));
		}
		catch(ServiceException e) {
			throw e;
		}
		catch (Exception e) {
			ScienzaLogger.logError(e);
			throw new ServiceException(VINCULATION_ERROR);
		}
		
		return response;
	}


	@Override
	public AffiliateBindingResponseDto fullBindAffiliate(AffiliateBindingRequestDto request, String token, Long sapId, String deviceType) throws ServiceException {

		AffiliateBindingResponseDto response = null;
		UserResponseDto userActualResponse = null;
		UserResponseDto userVinculadoResponse = null; 
	
		try 
		{
			Sesion sesion = sesionRepository.findByToken(token);
			Afiliado afiliado = sesion.getAfiliado();
			Afiliado afiliadoVinculadoNuevo = this.newAffiliate(request, deviceType);
			
			if(afiliadoVinculadoNuevo.getId().compareTo(afiliado.getId()) == 0) {
				throw new ServiceException("El afiliado no puede auto-vincularse");
			}
			for(Afiliado afiliadoAux : afiliado.getAfiliadosVinculados()) {
				if(afiliadoVinculadoNuevo.getSapId().compareTo(afiliadoAux.getSapId()) == 0)
					throw new ServiceException("El afiliado ya se encuentra vinculado");
			}
			
			afiliado.getAfiliadosVinculados().add(afiliadoVinculadoNuevo);
			afiliado.updateOnRepository(afiliadoRepository);
			
			/* Response */
			response = new AffiliateBindingResponseDto();
			userActualResponse = new UserResponseDto();
			userActualResponse.setAvatar(afiliado.getAvatar());
			userActualResponse.setNombre(afiliado.getNombre());
			userActualResponse.setApellido(afiliado.getApellido());
			userActualResponse.setcedulaIdentidad(afiliado.getcedulaIdentidad());
			userActualResponse.setSapId(afiliado.getSapId());
			userActualResponse.setVerificarPerfil(afiliado.getVerificarPerfil());
			userActualResponse.setPermisos(afiliado.getUsuario().getRol().getGrantsToString(afiliado));
			response.setUsuario(userActualResponse);

			for(Afiliado afiliadoVinculado : afiliado.getAfiliadosVinculados())
			{
				userVinculadoResponse = new UserResponseDto();
				userVinculadoResponse.setAvatar(afiliadoVinculado.getAvatar());
				userVinculadoResponse.setNombre(afiliadoVinculado.getNombre());
				userVinculadoResponse.setApellido(afiliadoVinculado.getApellido());
				userVinculadoResponse.setcedulaIdentidad(afiliadoVinculado.getcedulaIdentidad());
				userVinculadoResponse.setSapId(afiliadoVinculado.getSapId());
				userVinculadoResponse.setVerificarPerfil(afiliadoVinculado.getVerificarPerfil());
				userVinculadoResponse.setPermisos(afiliado.getUsuario().getRol().getGrantsToString(afiliadoVinculado));
				response.getOtrosUsuarios().add(userVinculadoResponse);
			}			
		}
		catch(ServiceException e) {
			throw e;
		}
		catch (Exception e) {

			ScienzaLogger.logError(e);
			throw new ServiceException(VINCULATION_ERROR);
		}
		
		return response;
	}


	@Override
	public AffiliateUnbindingResponseDto unbindAffiliate(AffiliateUnbindingRequestDto request, String token, Long sapId, String deviceType) throws ServiceException {
	
		AffiliateUnbindingResponseDto response = null;
		UserResponseDto userActualResponse = null;
		UserResponseDto userVinculadoResponse = null; 
		
		try 
		{
			Afiliado afiliado = sesionRepository.findByToken(token).getAfiliado();
			Afiliado afiliadoDesvinculado = null;
			
			for(Afiliado afiliadoVinculado : afiliado.getAfiliadosVinculados()) {
				if(afiliadoVinculado.getSapId().compareTo(request.getSapId()) == 0) {
					afiliadoDesvinculado = afiliadoVinculado;
				}
			}
			
			if(afiliadoDesvinculado == null)
				throw new ServiceException("El usuario no se encuentra vinculado");
			
			afiliado.getAfiliadosVinculados().remove(afiliadoDesvinculado);
			afiliado.updateOnRepository(afiliadoRepository);
			
			/* Response */
			response = new AffiliateUnbindingResponseDto();
			userActualResponse = new UserResponseDto();
			userActualResponse.setAvatar(afiliado.getAvatar());
			userActualResponse.setNombre(afiliado.getNombre());
			userActualResponse.setApellido(afiliado.getApellido());
			userActualResponse.setcedulaIdentidad(afiliado.getcedulaIdentidad());
			userActualResponse.setSapId(afiliado.getSapId());
			userActualResponse.setVerificarPerfil(afiliado.getVerificarPerfil());
			userActualResponse.setPermisos(afiliado.getUsuario().getRol().getGrantsToString(afiliado));
			response.setUsuario(userActualResponse);
			
			for(Afiliado afiliadoVinculado : afiliado.getAfiliadosVinculados())
			{
				userVinculadoResponse = new UserResponseDto();
				userVinculadoResponse.setAvatar(afiliadoVinculado.getAvatar());
				userVinculadoResponse.setNombre(afiliadoVinculado.getNombre());
				userVinculadoResponse.setApellido(afiliadoVinculado.getApellido());
				userVinculadoResponse.setcedulaIdentidad(afiliadoVinculado.getcedulaIdentidad());
				userVinculadoResponse.setSapId(afiliadoVinculado.getSapId());
				userVinculadoResponse.setVerificarPerfil(afiliadoVinculado.getVerificarPerfil());
				userVinculadoResponse.setPermisos(afiliado.getUsuario().getRol().getGrantsToString(afiliadoVinculado));
				response.getOtrosUsuarios().add(userVinculadoResponse);
			}
		}
		catch(ServiceException e) {
			throw e;
		}
		catch (Exception e) {
			ScienzaLogger.logError(e);
			throw new ServiceException(DESVINCULATION_ERROR);
		}
		
		return response;
	}
	

	/**
	 * Crea un nuevo afiliado en el sistema
	 * @param request
	 * @return
	 * @throws ServiceException
	 */
	private Afiliado newAffiliate(AffiliateBindingRequestDto request, String deviceType) throws Exception {
		
		Afiliado afiliado = afiliadoRepository.findBycedulaIdentidadAndSapId(request.getcedulaIdentidad(), request.getSapId());
		if(afiliado == null)
		{
			DTSCIENZAREGUSUARIOUYResponse remoteResponse = this.vincularUsuarioSAP(request, deviceType);
			
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
//			String address = afiliado.getCalle() + " " + afiliado.getCalleNumero() + ", " + afiliado.getLocalidad() + ", " + afiliado.getProvincia() + ", Argentina";
//			GoogleMapsFullDataResponseDto gmResponse = googleMapsService.getFullData(address, null, null, null);
//			if(gmResponse != null)
//			{
//				afiliado.setDepartamento(gmResponse.getProvincia());
//				afiliado.setLocalidad(gmResponse.getLocalidad());
//				afiliado.setCalle(gmResponse.getCalle());
//				afiliado.setCalleNumero(gmResponse.getCalleNumero() != null ? gmResponse.getCalleNumero() : afiliado.getCalleNumero());
//				afiliado.setCodigoPostal(gmResponse.getCodigoPostal() != null ? StringUtil.stripNonDigits(gmResponse.getCodigoPostal()) : afiliado.getCodigoPostal());
//				afiliado.setLatitud(gmResponse.getLatitud());
//				afiliado.setLongitud(gmResponse.getLongitud());
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
	private DTSCIENZAREGUSUARIOUYResponse vincularUsuarioSAP(AffiliateBindingRequestDto request, String deviceType) throws Exception {

		DTSCIENZAREGUSUARIOUYResponse remoteResponse = null;
		
		DTSCIENZAREGUSUARIOUY remoteRequest = new DTSCIENZAREGUSUARIOUY();
		remoteRequest.setPACIENTECI(request.getcedulaIdentidad().toString());
		remoteRequest.setPACIENTEID(request.getSapId().toString());
		remoteRequest.setREGISTRACION(deviceType);
		
		remoteResponse = affiliateBindingRemoteService.bindAffiliate(remoteRequest);
		
		if(!SAPResultadoEnum.STATUS_OK.getCodigo().equals(remoteResponse.getRESULTADO()))
			throw new Exception();
		
		return remoteResponse;
	}

}

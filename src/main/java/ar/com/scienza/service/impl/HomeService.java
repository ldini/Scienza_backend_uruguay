package ar.com.scienza.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.scienza.config.ScienzaLogger;
import ar.com.scienza.dto.AdminAvailabilityRequestDto;
import ar.com.scienza.dto.AffiliateHomeResponseDto;
import ar.com.scienza.entity.Administrador;
import ar.com.scienza.entity.Afiliado;
import ar.com.scienza.entity.Rol;
import ar.com.scienza.entity.Sesion;
import ar.com.scienza.enumerator.TipoDocumentacionEnum;
import ar.com.scienza.exception.ServiceException;
import ar.com.scienza.repository.AdministradorRepository;
import ar.com.scienza.repository.ConsultaRepository;
import ar.com.scienza.repository.DocumentacionRepository;
import ar.com.scienza.repository.NotificacionRepository;
import ar.com.scienza.repository.SesionRepository;
import ar.com.scienza.service.IHomeService;


@Service
@Transactional(rollbackFor=ServiceException.class)
public class HomeService implements IHomeService {

	@Autowired
	private SesionRepository sesionRepository;

	@Autowired
	private NotificacionRepository notificacionRepository;

	@Autowired
	private ConsultaRepository consultaRepository;
	
	@Autowired
	private DocumentacionRepository documentacionRepository;
	
	@Autowired
	private AdministradorRepository administradorRepository;


	@Override
	public AffiliateHomeResponseDto getHome(String token, Long sapId, String deviceType) throws ServiceException {

		AffiliateHomeResponseDto response = new AffiliateHomeResponseDto();
		
		try 
		{
			Afiliado afiliado = sesionRepository.findByToken(token).getAfiliado().getAfiliadoActivo(sapId);
			Rol rol = sesionRepository.findByToken(token).getAfiliado().getUsuario().getRol();
			if(afiliado == null) {
				throw new Exception();
			}
			
			response.setCoordinacionPendiente(false);
			response.setNotificacionesNoLeidas(notificacionRepository.countByAfiliadoIdAndLeidoFalseAndFechaDeleteIsNull(afiliado.getId()).intValue());
			response.setConsultasNoLeidas(consultaRepository.countByConsultasNoLeidas(afiliado.getId()).intValue());
			response.setPermiteEnvioDocumentacion(rol.getGrantsToString(afiliado).contains("DOCAFI"));
			response.setOtrosEstudiosSubidos(documentacionRepository.countByAfiliadoIdAndTipoDocumentacionAndFechaDeleteIsNull(afiliado.getId(), TipoDocumentacionEnum.OTROS_ESTUDIOS.getCodigo()).intValue());
			response.setRecetasSubidas(documentacionRepository.countByAfiliadoIdAndTipoDocumentacionAndFechaDeleteIsNull(afiliado.getId(), TipoDocumentacionEnum.RECETA_MEDICA.getCodigo()).intValue());

		}
		catch (Exception e) {
			ScienzaLogger.logError(e);
			throw new ServiceException("Error al obtener home del paciente");
		}
		
		return response;
	}


	@Override
	public void updateAvailability(AdminAvailabilityRequestDto request, String token, Long sapId, String deviceType) throws ServiceException {

		try 
		{			
			Sesion sesion = sesionRepository.findByToken(token);
			Administrador administrador = sesion.getAdministrador();
			if(administrador == null) {
				throw new Exception();
			}
			
			administrador.setOperativo(request.getAvailable());
			administrador.updateOnRepository(administradorRepository);
		}
		catch (Exception e) {
			ScienzaLogger.logError(e);
			throw new ServiceException("Error al actualizar la disponibilidad");
		}
	}
}

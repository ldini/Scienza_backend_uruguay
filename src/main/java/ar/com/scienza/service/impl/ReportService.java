package ar.com.scienza.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import ar.com.scienza.dto.ReportActionsAffiliateResponseDto;
import ar.com.scienza.transformer.ReportActionsAffiliateTRF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.scienza.config.ScienzaLogger;
import ar.com.scienza.dto.ReportUniverseAffiliateResponseDto;
import ar.com.scienza.exception.ServiceException;
import ar.com.scienza.repository.AfiliadoRepository;
import ar.com.scienza.service.IReportService;
import ar.com.scienza.transformer.ReportUniverseAffiliateTRF;


@Service
@Transactional(rollbackFor=ServiceException.class)
public class ReportService implements IReportService {

	@Autowired
	private AfiliadoRepository afiliadoRepository;

	
	@Override
	public List<ReportUniverseAffiliateResponseDto> getUniverseAffiliateList(String token, Long sapId, String deviceType) throws ServiceException {

		List<ReportUniverseAffiliateResponseDto> responseList = new ArrayList<ReportUniverseAffiliateResponseDto>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		try 
		{
			for(ReportUniverseAffiliateTRF reportTRF : afiliadoRepository.getUniverseAffiliateList())
			{
				ReportUniverseAffiliateResponseDto response = new ReportUniverseAffiliateResponseDto();

				response.setFechaAlta((reportTRF.getFechaAlta() != null ) ? sdf.format(reportTRF.getFechaAlta()) : "");
				response.setSapId(reportTRF.getSapId());
				response.setNombre((reportTRF.getNombre() != null) ? (reportTRF.getNombre()) : "");
				response.setApellido((reportTRF.getApellido() != null) ? (reportTRF.getApellido()) : "");
				response.setcedulaIdentidad((reportTRF.getcedulaIdentidad() != null) ? (reportTRF.getcedulaIdentidad()) : "");
				response.setNumeroAfiliado((reportTRF.getNumeroAfiliado() != null) ? (reportTRF.getNumeroAfiliado()) : "");
				response.setEmail((reportTRF.getEmail() != null) ? (reportTRF.getEmail()) : "");
				response.setSeguroMedico((reportTRF.getSeguroMedico() != null) ? (reportTRF.getSeguroMedico()): "");
				response.setFechaUltimaTransaccion((reportTRF.getFechaUltimaTransaccion() != null) ? sdf.format(reportTRF.getFechaUltimaTransaccion()) : "");
				responseList.add(response);
			}
		}
		catch (Exception e) {
			ScienzaLogger.logError(e);
			throw new ServiceException("Error al obtener Reporte Pacientes");
		}
		
		return responseList;
	}

	@Override
	public List<ReportActionsAffiliateResponseDto> getActionsAffiliateList(String token, Long sapId, String deviceType, String startDate, String finishDate) throws ServiceException {

		List<ReportActionsAffiliateResponseDto> responseList = new ArrayList<ReportActionsAffiliateResponseDto>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		try
		{
			for (ReportActionsAffiliateTRF reportTRF : afiliadoRepository.getActionsAffiliateList(startDate, finishDate)){

				ReportActionsAffiliateResponseDto response = new ReportActionsAffiliateResponseDto();

				response.setFechaAccion(sdf.format(reportTRF.getFechaAccion()));
				response.setAccion(reportTRF.getAccion());
				response.setTipoDispositivo(reportTRF.getTipoDispositivo());
				response.setSapId(reportTRF.getSapId());
				response.setSeguroMedico(reportTRF.getSeguroMedico());
				responseList.add(response);
			}
		}catch (Exception e) {
			ScienzaLogger.logError(e);
			throw new ServiceException("Error al obtener Reporte de Acciones de Pacientes");
		}

		return responseList;
	}
}

package ar.com.scienza.service;

import java.util.List;

import ar.com.scienza.dto.ReportActionsAffiliateResponseDto;
import ar.com.scienza.dto.ReportUniverseAffiliateResponseDto;
import ar.com.scienza.exception.ServiceException;


public interface IReportService {

	public List<ReportUniverseAffiliateResponseDto> getUniverseAffiliateList(String token, Long sapId, String deviceType) throws ServiceException;

	public List<ReportActionsAffiliateResponseDto> getActionsAffiliateList(String token, Long sapId, String deviceType, String fechaInicio, String fechaFin) throws ServiceException;
}

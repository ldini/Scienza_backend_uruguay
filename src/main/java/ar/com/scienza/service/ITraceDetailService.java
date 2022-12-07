package ar.com.scienza.service;

import ar.com.scienza.dto.TraceDetailResponseDto;
import ar.com.scienza.exception.ServiceException;


public interface ITraceDetailService {

	public TraceDetailResponseDto getTraceDetail(String code, String qRcode, String token, Long sapId, String deviceType) throws ServiceException;
	
}

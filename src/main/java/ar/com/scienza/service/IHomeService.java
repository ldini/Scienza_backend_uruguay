package ar.com.scienza.service;

import ar.com.scienza.dto.AdminAvailabilityRequestDto;
import ar.com.scienza.dto.AffiliateHomeResponseDto;
import ar.com.scienza.exception.ServiceException;


public interface IHomeService {

	public AffiliateHomeResponseDto getHome(String token, Long sapId, String deviceType) throws ServiceException;

	public void updateAvailability(AdminAvailabilityRequestDto request, String token, Long sapId, String deviceType) throws ServiceException;
	
}

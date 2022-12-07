package ar.com.scienza.service;

import ar.com.scienza.dto.AffiliateBindingRequestDto;
import ar.com.scienza.dto.AffiliateBindingResponseDto;
import ar.com.scienza.dto.AffiliateUnbindingRequestDto;
import ar.com.scienza.dto.AffiliateUnbindingResponseDto;
import ar.com.scienza.dto.UserResponseDto;
import ar.com.scienza.exception.ServiceException;


public interface IAffiliateBindingService {

	public UserResponseDto bindAffiliate(AffiliateBindingRequestDto request, String token, Long sapId, String deviceType) throws ServiceException;

	public AffiliateBindingResponseDto fullBindAffiliate(AffiliateBindingRequestDto request, String token, Long sapId, String deviceType) throws ServiceException;

	public AffiliateUnbindingResponseDto unbindAffiliate(AffiliateUnbindingRequestDto request, String token, Long sapId, String deviceType) throws ServiceException;
	
}

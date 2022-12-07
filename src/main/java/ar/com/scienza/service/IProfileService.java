package ar.com.scienza.service;

import java.util.List;

import ar.com.scienza.dto.AffiliateProfileEditRequestDto;
import ar.com.scienza.dto.AffiliateProfileEditResponseDto;
import ar.com.scienza.dto.AffiliateProfileResponseDto;
import ar.com.scienza.dto.PendingProfileAcceptRequestDto;
import ar.com.scienza.dto.PendingProfileRejectRequestDto;
import ar.com.scienza.dto.PendingProfileResponseDto;
import ar.com.scienza.dto.PendingProfileResultResponseDto;
import ar.com.scienza.exception.ServiceException;


public interface IProfileService {

	public AffiliateProfileResponseDto getAffiliateProfile(String token, Long sapId, String deviceType) throws ServiceException;

	public AffiliateProfileEditResponseDto editAffiliateProfile(AffiliateProfileEditRequestDto request, String token, Long sapId, String deviceType) throws ServiceException;

	public List<PendingProfileResultResponseDto> getPendingProfileList(String token, Long sapId, String deviceType) throws ServiceException;

	public PendingProfileResponseDto getPendingProfile(Integer bandejaId, String token, Long sapId, String deviceType) throws ServiceException;

	public void acceptPendingProfile(PendingProfileAcceptRequestDto request, String token, Long sapId, String deviceType) throws ServiceException;

	public void rejectPendingProfile(PendingProfileRejectRequestDto request, String token, Long sapId, String deviceType) throws ServiceException;
	
}

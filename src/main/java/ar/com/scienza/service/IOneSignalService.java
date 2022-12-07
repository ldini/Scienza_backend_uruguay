package ar.com.scienza.service;

import ar.com.scienza.dto.OneSignalRequestDto;
import ar.com.scienza.exception.ServiceException;


public interface IOneSignalService {

	public void updatePlayerId(OneSignalRequestDto request, String token, Long sapId, String deviceType) throws ServiceException;
	
}

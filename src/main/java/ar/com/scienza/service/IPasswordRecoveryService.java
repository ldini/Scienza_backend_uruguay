package ar.com.scienza.service;

import ar.com.scienza.dto.PasswordRecoveryRequestDto;
import ar.com.scienza.dto.PasswordRecoveryResponseDto;
import ar.com.scienza.exception.ServiceException;


public interface IPasswordRecoveryService {

	public PasswordRecoveryResponseDto recoverPassword(PasswordRecoveryRequestDto request, String token, Long sapId, String deviceType) throws ServiceException;
	
}

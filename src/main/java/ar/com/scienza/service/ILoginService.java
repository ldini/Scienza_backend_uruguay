package ar.com.scienza.service;

import ar.com.scienza.dto.LoginRequestDto;
import ar.com.scienza.dto.LoginResponseDto;
import ar.com.scienza.exception.ServiceException;


public interface ILoginService {

	public LoginResponseDto login(LoginRequestDto request, String token, Long sapId, String deviceType) throws ServiceException;

	public void logout(String token, Long sapId, String deviceType) throws ServiceException;
	
}

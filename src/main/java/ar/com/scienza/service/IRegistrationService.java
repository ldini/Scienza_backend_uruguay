package ar.com.scienza.service;

import ar.com.scienza.dto.*;
import ar.com.scienza.exception.CustomServiceException;
import ar.com.scienza.exception.ServiceException;
import freemarker.template.TemplateException;

import javax.mail.MessagingException;
import java.io.IOException;


public interface IRegistrationService {

	public RegistrationResponseDto registrate(RegistrationRequestDto request, String token, Long sapId, String deviceType) throws ServiceException, CustomServiceException;

	public RegistrationErrorResponseDto registrateError(RegistrationErrorRequestDto request, String token, Long sapId, String deviceType) throws ServiceException, MessagingException, IOException, TemplateException;

	public PendingRegistrationsResponseDto getPendingRegistrations(String token, Long sapId, String deviceType) throws ServiceException;

	public PendingRegistrationResponseDto getPendingRegistration(Integer bandejaId, String token, Long sapId, String deviceType) throws ServiceException;

	public void bindPendingRegistration(PendingRegistrationBindRequestDto request, String token, Long sapId, String deviceType) throws ServiceException;

	public void rejectPendingRegistration(PendingRegistrationRejectRequestDto request, String token, Long sapId, String deviceType) throws ServiceException;

	public void savePendingRegistration(PendingRegistrationSaveRequestDto request, String token, Long sapId, String deviceType)throws ServiceException ;
}

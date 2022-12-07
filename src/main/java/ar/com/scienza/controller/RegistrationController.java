package ar.com.scienza.controller;

import javax.mail.MessagingException;
import javax.validation.Valid;

import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.scienza.base.Authorize;
import ar.com.scienza.config.ScienzaLogger;
import ar.com.scienza.dto.RegistrationErrorRequestDto;
import ar.com.scienza.dto.RegistrationErrorResponseDto;
import ar.com.scienza.dto.RegistrationRequestDto;
import ar.com.scienza.dto.RegistrationResponseDto;
import ar.com.scienza.dto.ResponseDto;
import ar.com.scienza.entity.Transaction;
import ar.com.scienza.exception.CustomServiceException;
import ar.com.scienza.exception.ServiceException;
import ar.com.scienza.service.IRegistrationService;

import java.io.IOException;


@RestController
@EnableAutoConfiguration
public class RegistrationController {

	@Autowired
	private IRegistrationService registrationService;

	@Autowired
	private Transaction transaction;

	
	@Authorize("*")
	@RequestMapping(
		method = RequestMethod.POST, 
		value = "/registration", 
		produces = "application/json"
	)
	public @ResponseBody ResponseDto registrate(
		@RequestHeader(value="AppID") String appId,
		@RequestHeader(value="Token") String token,
		@RequestHeader(value="SapId") Long sapId,
		@RequestHeader(value="DeviceType") String deviceType,
		@RequestBody @Valid RegistrationRequestDto request, 
		BindingResult result, 
		Model model
	) {

		RegistrationResponseDto response = null;
		
		try
		{
			ScienzaLogger.logRequest(request, transaction);
			
			if(result.hasErrors()) {
				ObjectError error = result.getAllErrors().get(0);
				return ResponseDto.newError(transaction, error.getDefaultMessage());
			}
			
			response = this.registrationService.registrate(request, token, sapId, deviceType);
		}
		catch(ServiceException ex) {
			return ResponseDto.newError(transaction, ex.getMessage());
		}
		catch(CustomServiceException ex) {
			return ResponseDto.newCustomError(transaction, ex.getCode(), ex.getMessage());
		}
		
		return ResponseDto.newSuccess(transaction, response);
	}

	
	@Authorize("*")
	@RequestMapping(
		method = RequestMethod.POST, 
		value = "/registration/error", 
		produces = "application/json"
	)
	public @ResponseBody ResponseDto registrateError(
		@RequestHeader(value="AppID") String appId,
		@RequestHeader(value="Token") String token,
		@RequestHeader(value="SapId") Long sapId,
		@RequestHeader(value="DeviceType") String deviceType,
		@RequestBody @Valid RegistrationErrorRequestDto request, 
		BindingResult result, 
		Model model
	) {

		RegistrationErrorResponseDto response = null;
		
		try
		{
			ScienzaLogger.logRequest(request, transaction);
			
			if(result.hasErrors()) {
				ObjectError error = result.getAllErrors().get(0);
				return ResponseDto.newError(transaction, error.getDefaultMessage());
			}
			
			response = this.registrationService.registrateError(request, token, sapId, deviceType);
		}
		catch(ServiceException ex) {
			return ResponseDto.newError(transaction, ex.getMessage());
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}

		return ResponseDto.newSuccess(transaction, response);
	}
}

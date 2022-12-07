package ar.com.scienza.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.scienza.base.Authorize;
import ar.com.scienza.config.ScienzaLogger;
import ar.com.scienza.dto.PendingRegistrationBindRequestDto;
import ar.com.scienza.dto.PendingRegistrationRejectRequestDto;
import ar.com.scienza.dto.PendingRegistrationResponseDto;
import ar.com.scienza.dto.PendingRegistrationSaveRequestDto;
import ar.com.scienza.dto.PendingRegistrationsResponseDto;
import ar.com.scienza.dto.ResponseDto;
import ar.com.scienza.entity.Transaction;
import ar.com.scienza.exception.ServiceException;
import ar.com.scienza.service.IRegistrationService;


@RestController
@EnableAutoConfiguration
public class PendingRegistrationController {

	@Autowired
	private IRegistrationService registrationService;
	
	@Autowired
	private Transaction transaction;

	
	@Authorize("ALTPEN")
	@RequestMapping(
		method = RequestMethod.GET, 
		value = "/pendingRegistration", 
		produces = "application/json"
	)
	public @ResponseBody ResponseDto pendingRegistration(
		@RequestHeader(value="AppID") String appId,
		@RequestHeader(value="Token") String token,
		@RequestHeader(value="SapId") Long sapId,
		@RequestHeader(value="DeviceType") String deviceType
	) {

		PendingRegistrationsResponseDto response = new PendingRegistrationsResponseDto();
		
		try
		{		
			response = this.registrationService.getPendingRegistrations(token, sapId, deviceType);
		}
		catch(ServiceException ex) {
			return ResponseDto.newError(transaction, ex.getMessage());
		}
		
		return ResponseDto.newSuccess(transaction, response);
	}
	
	
	@Authorize("ALTPEN")
	@RequestMapping(
		method = RequestMethod.GET, 
		value = "/pendingRegistration/get", 
		produces = "application/json"
	)
	public @ResponseBody ResponseDto pendingRegistration(
		@RequestHeader(value="AppID") String appId,
		@RequestHeader(value="Token") String token,
		@RequestHeader(value="SapId") Long sapId,
		@RequestHeader(value="DeviceType") String deviceType,
		@RequestParam(name="idResult") Integer bandejaId
	) {

		PendingRegistrationResponseDto response = new PendingRegistrationResponseDto();
		
		try
		{		
			response = this.registrationService.getPendingRegistration(bandejaId, token, sapId, deviceType);
		}
		catch(ServiceException ex) {
			return ResponseDto.newError(transaction, ex.getMessage());
		}
		
		return ResponseDto.newSuccess(transaction, response);
	}

	@Authorize("ALTPEN")
	@RequestMapping(
			method = RequestMethod.POST,
			value = "/pendingRegistration/save",
			produces = "application/json"
	)
	public @ResponseBody ResponseDto savePendingRegistration(
			@RequestHeader(value="AppID") String appId,
			@RequestHeader(value="Token") String token,
			@RequestHeader(value="SapId") Long sapId,
			@RequestHeader(value="DeviceType") String deviceType,
			@RequestBody @Valid PendingRegistrationSaveRequestDto request,
			BindingResult result,
			Model model
	) {

		try
		{
			ScienzaLogger.logRequest(request, transaction);

			if(result.hasErrors()) {
				ObjectError error = result.getAllErrors().get(0);
				return ResponseDto.newError(transaction, error.getDefaultMessage());
			}

			this.registrationService.savePendingRegistration(request, token, sapId, deviceType);
		}
		catch(ServiceException ex) {
			return ResponseDto.newError(transaction, ex.getMessage());
		}

		return ResponseDto.newSuccess(transaction, null);
	}


	@Authorize("ALTPEN")
	@RequestMapping(
		method = RequestMethod.POST, 
		value = "/pendingRegistration/bind", 
		produces = "application/json"
	)
	public @ResponseBody ResponseDto bindPendingRegistration(
		@RequestHeader(value="AppID") String appId,
		@RequestHeader(value="Token") String token,
		@RequestHeader(value="SapId") Long sapId,
		@RequestHeader(value="DeviceType") String deviceType,
		@RequestBody @Valid PendingRegistrationBindRequestDto request, 
		BindingResult result, 
		Model model
	) {
		
		try
		{
			ScienzaLogger.logRequest(request, transaction);
			
			if(result.hasErrors()) {
				ObjectError error = result.getAllErrors().get(0);
				return ResponseDto.newError(transaction, error.getDefaultMessage());
			}
			
			this.registrationService.bindPendingRegistration(request, token, sapId, deviceType);
		}
		catch(ServiceException ex) {
			return ResponseDto.newError(transaction, ex.getMessage());
		}
		
		return ResponseDto.newSuccess(transaction, null);
	}


	@Authorize("ALTPEN")
	@RequestMapping(
		method = RequestMethod.POST, 
		value = "/pendingRegistration/reject", 
		produces = "application/json"
	)
	public @ResponseBody ResponseDto rejectPendingRegistration(
		@RequestHeader(value="AppID") String appId,
		@RequestHeader(value="Token") String token,
		@RequestHeader(value="SapId") Long sapId,
		@RequestHeader(value="DeviceType") String deviceType,
		@RequestBody @Valid PendingRegistrationRejectRequestDto request, 
		BindingResult result, 
		Model model
	) {
		
		try
		{
			ScienzaLogger.logRequest(request, transaction);
			
			if(result.hasErrors()) {
				ObjectError error = result.getAllErrors().get(0);
				return ResponseDto.newError(transaction, error.getDefaultMessage());
			}
			
			this.registrationService.rejectPendingRegistration(request, token, sapId, deviceType);
		}
		catch(ServiceException ex) {
			return ResponseDto.newError(transaction, ex.getMessage());
		}
		
		return ResponseDto.newSuccess(transaction, null);
	}
}

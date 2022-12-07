package ar.com.scienza.controller;

import java.util.ArrayList;
import java.util.List;

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
import ar.com.scienza.dto.BodyDto;
import ar.com.scienza.dto.PendingProfileAcceptRequestDto;
import ar.com.scienza.dto.PendingProfileRejectRequestDto;
import ar.com.scienza.dto.PendingProfileResponseDto;
import ar.com.scienza.dto.PendingProfileResultResponseDto;
import ar.com.scienza.dto.ResponseDto;
import ar.com.scienza.dto.ResponseListDto;
import ar.com.scienza.entity.Transaction;
import ar.com.scienza.exception.ServiceException;
import ar.com.scienza.service.IProfileService;


@RestController
@EnableAutoConfiguration
public class PendingProfileController {

	@Autowired
	private IProfileService profileService;
	
	@Autowired
	private Transaction transaction;

	
	@Authorize("PERPEN")
	@RequestMapping(
		method = RequestMethod.GET, 
		value = "/pendingProfile", 
		produces = "application/json"
	)
	public @ResponseBody ResponseListDto getPendingProfileList(
		@RequestHeader(value="AppID") String appId,
		@RequestHeader(value="Token") String token,
		@RequestHeader(value="SapId") Long sapId,
		@RequestHeader(value="DeviceType") String deviceType
	) {

		List<BodyDto> responseList = new ArrayList<BodyDto>();
		
		try
		{		
			List<PendingProfileResultResponseDto> list = this.profileService.getPendingProfileList(token, sapId, deviceType);
			responseList.addAll(list);
		}
		catch(ServiceException ex) {
			return ResponseListDto.newError(transaction, ex.getMessage());
		}
		
		return ResponseListDto.newSuccess(transaction, responseList);
	}
	
	
	@Authorize("PERPEN")
	@RequestMapping(
		method = RequestMethod.GET, 
		value = "/pendingProfile/get", 
		produces = "application/json"
	)
	public @ResponseBody ResponseDto getPendingProfile(
		@RequestHeader(value="AppID") String appId,
		@RequestHeader(value="Token") String token,
		@RequestHeader(value="SapId") Long sapId,
		@RequestHeader(value="DeviceType") String deviceType,
		@RequestParam(name="idResult") Integer bandejaId
	) {

		PendingProfileResponseDto response = new PendingProfileResponseDto();
		
		try
		{		
			response = this.profileService.getPendingProfile(bandejaId, token, sapId, deviceType);
		}
		catch(ServiceException ex) {
			return ResponseDto.newError(transaction, ex.getMessage());
		}
		
		return ResponseDto.newSuccess(transaction, response);
	}


	@Authorize("PERPEN")
	@RequestMapping(
		method = RequestMethod.POST, 
		value = "/pendingProfile/accept", 
		produces = "application/json"
	)
	public @ResponseBody ResponseDto acceptPendingProfile(
		@RequestHeader(value="AppID") String appId,
		@RequestHeader(value="Token") String token,
		@RequestHeader(value="SapId") Long sapId,
		@RequestHeader(value="DeviceType") String deviceType,
		@RequestBody @Valid PendingProfileAcceptRequestDto request, 
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
			
			this.profileService.acceptPendingProfile(request, token, sapId, deviceType);
		}
		catch(ServiceException ex) {
			return ResponseDto.newError(transaction, ex.getMessage());
		}
		
		return ResponseDto.newSuccess(transaction, null);
	}


	@Authorize("PERPEN")
	@RequestMapping(
		method = RequestMethod.POST, 
		value = "/pendingProfile/reject", 
		produces = "application/json"
	)
	public @ResponseBody ResponseDto rejectPendingProfile(
		@RequestHeader(value="AppID") String appId,
		@RequestHeader(value="Token") String token,
		@RequestHeader(value="SapId") Long sapId,
		@RequestHeader(value="DeviceType") String deviceType,
		@RequestBody @Valid PendingProfileRejectRequestDto request, 
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
			
			this.profileService.rejectPendingProfile(request, token, sapId, deviceType);
		}
		catch(ServiceException ex) {
			return ResponseDto.newError(transaction, ex.getMessage());
		}
		
		return ResponseDto.newSuccess(transaction, null);
	}
}

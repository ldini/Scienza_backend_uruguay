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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.scienza.base.Authorize;
import ar.com.scienza.config.ScienzaLogger;
import ar.com.scienza.dto.AffiliateProfileEditRequestDto;
import ar.com.scienza.dto.AffiliateProfileEditResponseDto;
import ar.com.scienza.dto.AffiliateProfileResponseDto;
import ar.com.scienza.dto.ResponseDto;
import ar.com.scienza.entity.Transaction;
import ar.com.scienza.exception.ServiceException;
import ar.com.scienza.service.IProfileService;


@RestController
@EnableAutoConfiguration
public class ProfileController {

	@Autowired
	private IProfileService profileService;

	@Autowired
	private Transaction transaction;

	
	@Authorize("PERAFI")
	@RequestMapping(
		method = RequestMethod.GET, 
		value = "/affiliateProfile/get", 
		produces = "application/json"
	)
	public @ResponseBody ResponseDto getAffiliateProfile(
		@RequestHeader(value="AppID") String appId,
		@RequestHeader(value="Token") String token,
		@RequestHeader(value="SapId") Long sapId,
		@RequestHeader(value="DeviceType") String deviceType
	) {

		AffiliateProfileResponseDto response = null;
		
		try
		{		
			response = this.profileService.getAffiliateProfile(token, sapId, deviceType);
		}
		catch(ServiceException ex) {
			return ResponseDto.newError(transaction, ex.getMessage());
		}
		
		return ResponseDto.newSuccess(transaction, response);
	}


	@Authorize("PERAFI")
	@RequestMapping(
		method = RequestMethod.POST, 
		value = "/affiliateProfile/edit", 
		produces = "application/json"
	)
	public @ResponseBody ResponseDto editAffiliateProfile(
		@RequestHeader(value="AppID") String appId,
		@RequestHeader(value="Token") String token,
		@RequestHeader(value="SapId") Long sapId,
		@RequestHeader(value="DeviceType") String deviceType,
		@RequestBody @Valid AffiliateProfileEditRequestDto request, 
		BindingResult result, 
		Model model
	) {
		
		AffiliateProfileEditResponseDto response = null;
		
		try
		{
			ScienzaLogger.logRequest(request, transaction);
			
			if(result.hasErrors()) {
				ObjectError error = result.getAllErrors().get(0);
				return ResponseDto.newError(transaction, error.getDefaultMessage());
			}
			
			response = this.profileService.editAffiliateProfile(request, token, sapId, deviceType);
		}
		catch(ServiceException ex) {
			return ResponseDto.newError(transaction, ex.getMessage());
		}
		
		return ResponseDto.newSuccess(transaction, response);
	}
}

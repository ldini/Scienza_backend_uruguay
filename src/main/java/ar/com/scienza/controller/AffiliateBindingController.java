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
import ar.com.scienza.dto.AffiliateBindingRequestDto;
import ar.com.scienza.dto.AffiliateBindingResponseDto;
import ar.com.scienza.dto.AffiliateUnbindingRequestDto;
import ar.com.scienza.dto.AffiliateUnbindingResponseDto;
import ar.com.scienza.dto.ResponseDto;
import ar.com.scienza.dto.UserResponseDto;
import ar.com.scienza.entity.Transaction;
import ar.com.scienza.exception.ServiceException;
import ar.com.scienza.service.IAffiliateBindingService;


@RestController
@EnableAutoConfiguration
public class AffiliateBindingController {

	@Autowired
	private IAffiliateBindingService affiliateBindingService;
	
	@Autowired
	private Transaction transaction;


	@Authorize("ADDAFI")
	@RequestMapping(
		method = RequestMethod.POST, 
		value = "/bindAffiliate", 
		produces = "application/json"
	)
	public @ResponseBody ResponseDto bindAffiliate(
		@RequestHeader(value="AppID") String appId,
		@RequestHeader(value="Token") String token,
		@RequestHeader(value="SapId") Long sapId,
		@RequestHeader(value="DeviceType") String deviceType,
		@RequestBody @Valid AffiliateBindingRequestDto request, 
		BindingResult result, 
		Model model
	) {
		
		UserResponseDto response = null;
		
		try
		{
			ScienzaLogger.logRequest(request, transaction);
			
			if(result.hasErrors()) {
				ObjectError error = result.getAllErrors().get(0);
				return ResponseDto.newError(transaction, error.getDefaultMessage());
			}
			
			response = this.affiliateBindingService.bindAffiliate(request, token, sapId, deviceType);
		}
		catch(ServiceException ex) {
			return ResponseDto.newError(transaction, ex.getMessage());
		}
		
		return ResponseDto.newSuccess(transaction, response);
	}


	@Authorize("ADDAFI")
	@RequestMapping(
		method = RequestMethod.POST, 
		value = "/fullBindAffiliate", 
		produces = "application/json"
	)
	public @ResponseBody ResponseDto fullBindAffiliate(
		@RequestHeader(value="AppID") String appId,
		@RequestHeader(value="Token") String token,
		@RequestHeader(value="SapId") Long sapId,
		@RequestHeader(value="DeviceType") String deviceType,
		@RequestBody @Valid AffiliateBindingRequestDto request, 
		BindingResult result, 
		Model model
	) {
		
		AffiliateBindingResponseDto response = null;
		
		try
		{
			ScienzaLogger.logRequest(request, transaction);
			
			if(result.hasErrors()) {
				ObjectError error = result.getAllErrors().get(0);
				return ResponseDto.newError(transaction, error.getDefaultMessage());
			}
			
			response = this.affiliateBindingService.fullBindAffiliate(request, token, sapId, deviceType);
		}
		catch(ServiceException ex) {
			return ResponseDto.newError(transaction, ex.getMessage());
		}
		
		return ResponseDto.newSuccess(transaction, response);
	}

	
	@Authorize("DELAFI")
	@RequestMapping(
		method = RequestMethod.POST, 
		value = "/unbindAffiliate", 
		produces = "application/json"
	)
	public @ResponseBody ResponseDto unbindAffiliate(
		@RequestHeader(value="AppID") String appId,
		@RequestHeader(value="Token") String token,
		@RequestHeader(value="SapId") Long sapId,
		@RequestHeader(value="DeviceType") String deviceType,
		@RequestBody @Valid AffiliateUnbindingRequestDto request, 
		BindingResult result, 
		Model model
	) {
		
		AffiliateUnbindingResponseDto response = null;
		
		try
		{
			ScienzaLogger.logRequest(request, transaction);
			
			if(result.hasErrors()) {
				ObjectError error = result.getAllErrors().get(0);
				return ResponseDto.newError(transaction, error.getDefaultMessage());
			}
			
			response = this.affiliateBindingService.unbindAffiliate(request, token, sapId, deviceType);
		}
		catch(ServiceException ex) {
			return ResponseDto.newError(transaction, ex.getMessage());
		}
		
		return ResponseDto.newSuccess(transaction, response);
	}
}

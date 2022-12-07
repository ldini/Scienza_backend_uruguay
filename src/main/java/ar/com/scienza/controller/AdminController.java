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
import ar.com.scienza.dto.AdminAvailabilityRequestDto;
import ar.com.scienza.dto.ResponseDto;
import ar.com.scienza.entity.Transaction;
import ar.com.scienza.exception.ServiceException;
import ar.com.scienza.service.IHomeService;


@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/admin")
public class AdminController {

	@Autowired
	private IHomeService homeService;
	
	@Autowired
	private Transaction transaction;

	
	@Authorize("DSHADM")
	@RequestMapping(
		method = RequestMethod.POST, 
		value = "/updateAvailability", 
		produces = "application/json"
	)
	public @ResponseBody ResponseDto updateAvailability(
		@RequestHeader(value="AppID") String appId,
		@RequestHeader(value="Token") String token,
		@RequestHeader(value="SapId") Long sapId,
		@RequestHeader(value="DeviceType") String deviceType,
		@RequestBody @Valid AdminAvailabilityRequestDto request, 
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
			
			this.homeService.updateAvailability(request, token, sapId, deviceType);
		}
		catch(ServiceException ex) {
			return ResponseDto.newError(transaction, ex.getMessage());
		}

		/* Anulo la persistencia de la Tx */
		transaction.setSesion(null);
		
		return ResponseDto.newSuccess(transaction, null);
	}
}

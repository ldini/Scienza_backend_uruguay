package ar.com.scienza.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.scienza.base.Authorize;
import ar.com.scienza.dto.AffiliateHomeResponseDto;
import ar.com.scienza.dto.ResponseDto;
import ar.com.scienza.entity.Transaction;
import ar.com.scienza.exception.ServiceException;
import ar.com.scienza.service.IHomeService;


@RestController
@EnableAutoConfiguration
public class AffiliateController {

	@Autowired
	private IHomeService homeService;
	
	@Autowired
	private Transaction transaction;

	
	@Authorize("DSHAFI")
	@RequestMapping(
		method = RequestMethod.GET, 
		value = "/home",
		produces = "application/json"
	)
	public @ResponseBody ResponseDto getHome(
		@RequestHeader(value="AppID") String appId,
		@RequestHeader(value="Token") String token,
		@RequestHeader(value="SapId") Long sapId,
		@RequestHeader(value="DeviceType") String deviceType
	) {

		AffiliateHomeResponseDto response = null;
		
		try
		{		
			response = homeService.getHome(token, sapId, deviceType);
		}
		catch(ServiceException ex) {
			return ResponseDto.newError(transaction, ex.getMessage());
		}
		
		/* Anulo la persistencia de la Tx */
		transaction.setSesion(null);
		
		return ResponseDto.newSuccess(transaction, response);
	}
}

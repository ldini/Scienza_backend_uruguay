package ar.com.scienza.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.scienza.base.Authorize;
import ar.com.scienza.dto.GoogleMapsAddressResponseDto;
import ar.com.scienza.dto.GoogleMapsCoordsResponseDto;
import ar.com.scienza.dto.ResponseDto;
import ar.com.scienza.entity.Transaction;
import ar.com.scienza.exception.ServiceException;
import ar.com.scienza.service.IGoogleMapsService;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/googlemaps")
public class GoogleMapsController {

	@Autowired
	private IGoogleMapsService googleMapsService;
	
	@Autowired
	private Transaction transaction;

	
	@Authorize("GOOMAP")
	@RequestMapping(
		method = RequestMethod.GET, 
		value = "/coords", 
		produces = "application/json"
	)
	public @ResponseBody ResponseDto getCoords(
		@RequestHeader(value="AppID") String appId,
		@RequestHeader(value="Token") String token,
		@RequestHeader(value="SapId") Long sapId,
		@RequestHeader(value="DeviceType") String deviceType,
		@RequestParam(name="address") String address
	) {

		GoogleMapsCoordsResponseDto response = new GoogleMapsCoordsResponseDto();
		
		try
		{		
			response = this.googleMapsService.getCoords(address, token, sapId, deviceType);
		}
		catch(ServiceException ex) {
			return ResponseDto.newError(transaction, ex.getMessage());
		}
		
		return ResponseDto.newSuccess(transaction, response);
	}

	
	@Authorize("GOOMAP")
	@RequestMapping(
		method = RequestMethod.GET, 
		value = "/address", 
		produces = "application/json"
	)
	public @ResponseBody ResponseDto getAddress(
		@RequestHeader(value="AppID") String appId,
		@RequestHeader(value="Token") String token,
		@RequestHeader(value="SapId") Long sapId,
		@RequestHeader(value="DeviceType") String deviceType,
		@RequestParam(name="latitude") String latitude,
		@RequestParam(name="longitude") String longitude
	) {

		GoogleMapsAddressResponseDto response = new GoogleMapsAddressResponseDto();
		
		try
		{		
			response = this.googleMapsService.getAddress(latitude, longitude, token, sapId, deviceType);
		}
		catch(ServiceException ex) {
			return ResponseDto.newError(transaction, ex.getMessage());
		}
		
		return ResponseDto.newSuccess(transaction, response);
	}
}

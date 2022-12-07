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
import ar.com.scienza.dto.ResponseDto;
import ar.com.scienza.dto.TraceDetailResponseDto;
import ar.com.scienza.entity.Transaction;
import ar.com.scienza.exception.ServiceException;
import ar.com.scienza.service.ITraceDetailService;


@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/trace")
public class TraceDetailController {

	@Autowired
	private ITraceDetailService traceDetailService;
	
	@Autowired
	private Transaction transaction;
	
	
	@Authorize("VERTRA")
	@RequestMapping(
		method = RequestMethod.GET, 
		value = "/get", 
		produces = "application/json"
	)
	public @ResponseBody ResponseDto getTraceDetail(
		@RequestHeader(value="AppID") String appId,
		@RequestHeader(value="Token") String token,
		@RequestHeader(value="SapId") Long sapId,
		@RequestHeader(value="DeviceType") String deviceType,
		@RequestParam(name="code") String code,
		@RequestParam(name="QRcode") String QRcode
	) {

		TraceDetailResponseDto response = null;
		
		try
		{		
			response = this.traceDetailService.getTraceDetail(code, QRcode, token, sapId, deviceType);
		}
		catch(ServiceException ex) {
			return ResponseDto.newError(transaction, ex.getMessage());
		}
		
		return ResponseDto.newSuccess(transaction, response);
	}

}

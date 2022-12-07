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
import ar.com.scienza.dto.AdminQueryCloseRequestDto;
import ar.com.scienza.dto.AdminQueryResponseDto;
import ar.com.scienza.dto.AdminQueryResultDetailResponseDto;
import ar.com.scienza.dto.AdminQueryResultResponseDto;
import ar.com.scienza.dto.AdminQueryTakeRequestDto;
import ar.com.scienza.dto.BodyDto;
import ar.com.scienza.dto.ResponseDto;
import ar.com.scienza.dto.ResponseListDto;
import ar.com.scienza.entity.Transaction;
import ar.com.scienza.exception.ServiceException;
import ar.com.scienza.service.IQueryService;


@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/adminQuery")
public class AdminQueryController {

	@Autowired
	private IQueryService queryService;
	
	@Autowired
	private Transaction transaction;

	
	@Authorize("CONPEN")
	@RequestMapping(
		method = RequestMethod.GET, 
		value = "/pending/list", 
		produces = "application/json"
	)
	public @ResponseBody ResponseDto getPendingQueryList(
		@RequestHeader(value="AppID") String appId,
		@RequestHeader(value="Token") String token,
		@RequestHeader(value="SapId") Long sapId,
		@RequestHeader(value="DeviceType") String deviceType
	) {

		AdminQueryResultResponseDto response = null;
		
		try
		{		
			response = queryService.getPendingQueryList(token, sapId, deviceType);
		}
		catch(ServiceException ex) {
			return ResponseDto.newError(transaction, ex.getMessage());
		}
		
		/* Anulo la persistencia de la Tx */
		transaction.setSesion(null);
		
		return ResponseDto.newSuccess(transaction, response);
	}

	
	@Authorize("CONPEN")
	@RequestMapping(
		method = RequestMethod.GET,
		value = "/history/list", 
		produces = "application/json"
	)
	public @ResponseBody ResponseListDto getHistoryQueryList(
		@RequestHeader(value="AppID") String appId,
		@RequestHeader(value="Token") String token,
		@RequestHeader(value="SapId") Long sapId,
		@RequestHeader(value="DeviceType") String deviceType,
		@RequestParam(name="idQuery") Integer queryId,
		@RequestParam(name="affiliateSapId") Long affiliateSapId,
		@RequestParam(name="firstName")  String firstName,
		@RequestParam(name="lastName") String lastName
	) {

		List<BodyDto> responseList = new ArrayList<BodyDto>();
		
		try
		{		
			List<AdminQueryResultDetailResponseDto> list = queryService.getHistoryQueryList(queryId, affiliateSapId, firstName , lastName , token, sapId, deviceType);
			responseList.addAll(list);
		}
		catch(ServiceException ex) {
			return ResponseListDto.newError(transaction, ex.getMessage());
		}
		
		/* Anulo la persistencia de la Tx */
		transaction.setSesion(null);
		
		return ResponseListDto.newSuccess(transaction, responseList);
	}


	@Authorize("CONPEN")
	@RequestMapping(
		method = RequestMethod.POST, 
		value = "/take", 
		produces = "application/json"
	)
	public @ResponseBody ResponseDto takeQuery(
		@RequestHeader(value="AppID") String appId,
		@RequestHeader(value="Token") String token,
		@RequestHeader(value="SapId") Long sapId,
		@RequestHeader(value="DeviceType") String deviceType,
		@RequestBody @Valid AdminQueryTakeRequestDto request, 
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
			
			queryService.takeQuery(request, token, sapId, deviceType);
		}
		catch(ServiceException ex) {
			return ResponseDto.newError(transaction, ex.getMessage());
		}
		
		return ResponseDto.newSuccess(transaction, null);
	}
	
	
	@Authorize("CONPEN")
	@RequestMapping(
		method = RequestMethod.GET, 
		value = "/get", 
		produces = "application/json"
	)
	public @ResponseBody ResponseDto getAdminQuery(
		@RequestHeader(value="AppID") String appId,
		@RequestHeader(value="Token") String token,
		@RequestHeader(value="SapId") Long sapId,
		@RequestHeader(value="DeviceType") String deviceType,
		@RequestParam(name="idQuery") Integer queryId
	) {

		AdminQueryResponseDto response = null;
		
		try
		{		
			response = queryService.getAdminQuery(queryId, token, sapId, deviceType);
		}
		catch(ServiceException ex) {
			return ResponseDto.newError(transaction, ex.getMessage());
		}
		
		return ResponseDto.newSuccess(transaction, response);
	}


	@Authorize("CONPEN")
	@RequestMapping(
		method = RequestMethod.POST, 
		value = "/close", 
		produces = "application/json"
	)
	public @ResponseBody ResponseDto closeQuery(
		@RequestHeader(value="AppID") String appId,
		@RequestHeader(value="Token") String token,
		@RequestHeader(value="SapId") Long sapId,
		@RequestHeader(value="DeviceType") String deviceType,
		@RequestBody @Valid AdminQueryCloseRequestDto request, 
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
			
			queryService.closeQuery(request, token, sapId, deviceType);
		}
		catch(ServiceException ex) {
			return ResponseDto.newError(transaction, ex.getMessage());
		}
		
		return ResponseDto.newSuccess(transaction, null);
	}
}

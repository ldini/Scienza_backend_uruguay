package ar.com.scienza.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import ar.com.scienza.dto.*;
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
import ar.com.scienza.entity.Transaction;
import ar.com.scienza.exception.ServiceException;
import ar.com.scienza.service.IQueryService;


@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/query")
public class QueryController {

	@Autowired
	private IQueryService queryService;

	@Autowired
	private Transaction transaction;
	
	
	@Authorize("CONAFI")
	@RequestMapping(
		method = RequestMethod.GET, 
		value = "/list",
		produces = "application/json"
	)
	public @ResponseBody ResponseListDto getQueryList(
		@RequestHeader(value="AppID") String appId,
		@RequestHeader(value="Token") String token,
		@RequestHeader(value="SapId") Long sapId,
		@RequestHeader(value="DeviceType") String deviceType
	) {

		List<BodyDto> responseList = new ArrayList<BodyDto>();
		
		try
		{	
			List<QueryResultResponseDto> list = queryService.getQueryList(token, sapId, deviceType);
			responseList.addAll(list);
		}
		catch(ServiceException ex) {
			return ResponseListDto.newError(transaction, ex.getMessage());
		}
		
		return ResponseListDto.newSuccess(transaction, responseList);
	}
	
	
	@Authorize("CONAFI")
	@RequestMapping(
		method = RequestMethod.GET, 
		value = "/get", 
		produces = "application/json"
	)
	public @ResponseBody ResponseDto getQuery(
		@RequestHeader(value="AppID") String appId,
		@RequestHeader(value="Token") String token,
		@RequestHeader(value="SapId") Long sapId,
		@RequestHeader(value="DeviceType") String deviceType,
		@RequestParam(name="idQuery") Integer consultaId
	) {

		QueryResponseDto response = null;
		
		try
		{		
			response = queryService.getQuery(consultaId, token, sapId, deviceType);
		}
		catch(ServiceException ex) {
			return ResponseDto.newError(transaction, ex.getMessage());
		}
		
		return ResponseDto.newSuccess(transaction, response);
	}


	@Authorize("CONAFI")
	@RequestMapping(
		method = RequestMethod.POST, 
		value = "/create", 
		produces = "application/json"
	)
	public @ResponseBody ResponseDto createQuery(
		@RequestHeader(value="AppID") String appId,
		@RequestHeader(value="Token") String token,
		@RequestHeader(value="SapId") Long sapId,
		@RequestHeader(value="DeviceType") String deviceType,
		@RequestBody @Valid QueryCreateRequestDto request, 
		BindingResult result, 
		Model model
	) {

		QueryCreateResponseDto response = null;
		
		try
		{
			ScienzaLogger.logRequest(request, transaction);
			
			if(result.hasErrors()) {
				ObjectError error = result.getAllErrors().get(0);
				return ResponseDto.newError(transaction, error.getDefaultMessage());
			}
			
			response = queryService.createQuery(request, token, sapId, deviceType);
		}
		catch(ServiceException ex) {
			return ResponseDto.newError(transaction, ex.getMessage());
		}
		
		return ResponseDto.newSuccess(transaction, response);
	}
	

	@Authorize("CONAFI")
	@RequestMapping(
		method = RequestMethod.POST, 
		value = "/delete",
		produces = "application/json"
	)
	public @ResponseBody ResponseDto deleteQuery(
		@RequestHeader(value="AppID") String appId,
		@RequestHeader(value="Token") String token,
		@RequestHeader(value="SapId") Long sapId,
		@RequestHeader(value="DeviceType") String deviceType,
		@RequestBody @Valid QueryDeleteRequestDto request, 
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
			
			queryService.deleteQuery(request, token, sapId, deviceType);
		}
		catch(ServiceException ex) {
			return ResponseDto.newError(transaction, ex.getMessage());
		}
		
		return ResponseDto.newSuccess(transaction, null);
	}
	

	@Authorize("CONAFI")
	@RequestMapping(
		method = RequestMethod.POST,
		value = "/deletebatch",
		produces = "application/json"
	)
	public @ResponseBody ResponseDto deleteQueryBatch(
		@RequestHeader(value="AppID") String appId,
		@RequestHeader(value="Token") String token,
		@RequestHeader(value="SapId") Long sapId,
		@RequestHeader(value="DeviceType") String deviceType,
		@RequestBody QueryBatchDeleteRequestDto request,
		BindingResult result,
		Model model
	) {
		QueryBatchDeleteResponseDto responseDto = null;
		
		try
		{
			ScienzaLogger.logRequest(request, transaction);

			if(result.hasErrors()) {
				ObjectError error = result.getAllErrors().get(0);
				return ResponseDto.newError(transaction, error.getDefaultMessage());
			}

			responseDto = queryService.deleteQueryBatch(request, token, sapId, deviceType);
		}
		catch(ServiceException ex) {
			return ResponseDto.newError(transaction, ex.getMessage());
		}

		return ResponseDto.newSuccess(transaction, responseDto);
	}
}

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
import ar.com.scienza.dto.ResponseDto;
import ar.com.scienza.dto.ResponseListDto;
import ar.com.scienza.dto.TemplateRequestDto;
import ar.com.scienza.dto.TemplateResponseDto;
import ar.com.scienza.dto.TemplateResultResponseDto;
import ar.com.scienza.entity.Transaction;
import ar.com.scienza.exception.ServiceException;
import ar.com.scienza.service.ITemplateService;


@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/template")
public class TemplateController {
	
	@Autowired
	private ITemplateService templateService;
	
	@Autowired
	private Transaction transaction;
	
	
	@Authorize("TEMPLA")
	@RequestMapping(
		method = RequestMethod.GET, 
		value = "/list", 
		produces = "application/json"
	)
	public @ResponseBody ResponseListDto getTemplateList(
		@RequestHeader(value="AppID") String appId,
		@RequestHeader(value="Token") String token,
		@RequestHeader(value="SapId") Long sapId,
		@RequestHeader(value="DeviceType") String deviceType
	) {

		List<BodyDto> responseList = new ArrayList<BodyDto>();
		
		try
		{		
			List<TemplateResultResponseDto> list = this.templateService.getTemplateList(token, sapId, deviceType);
			responseList.addAll(list);
		}
		catch(ServiceException ex) {
			return ResponseListDto.newError(transaction, ex.getMessage());
		}
		
		return ResponseListDto.newSuccess(transaction, responseList);
	}

	
	@Authorize("TEMPLA")
	@RequestMapping(
		method = RequestMethod.GET, 
		value = "/get", 
		produces = "application/json"
	)
	public @ResponseBody ResponseDto getTemplate(
		@RequestHeader(value="AppID") String appId,
		@RequestHeader(value="Token") String token,
		@RequestHeader(value="SapId") Long sapId,
		@RequestHeader(value="DeviceType") String deviceType,
		@RequestParam(name="idTemplate") Integer templateId
	) {

		TemplateResponseDto response = null;
		
		try
		{		
			response = this.templateService.getTemplate(templateId, token, sapId, deviceType);
		}
		catch(ServiceException ex) {
			return ResponseDto.newError(transaction, ex.getMessage());
		}
		
		return ResponseDto.newSuccess(transaction, response);
	}


	@Authorize("TEMPLA")
	@RequestMapping(
		method = RequestMethod.POST, 
		value = "/create", 
		produces = "application/json"
	)
	public @ResponseBody ResponseDto createTemplate(
		@RequestHeader(value="AppID") String appId,
		@RequestHeader(value="Token") String token,
		@RequestHeader(value="SapId") Long sapId,
		@RequestHeader(value="DeviceType") String deviceType,
		@RequestBody @Valid TemplateRequestDto request, 
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
			
			this.templateService.createTemplate(request, token, sapId, deviceType);
		}
		catch(ServiceException ex) {
			return ResponseDto.newError(transaction, ex.getMessage());
		}
		
		return ResponseDto.newSuccess(transaction, null);
	}


	@Authorize("TEMPLA")
	@RequestMapping(
		method = RequestMethod.POST, 
		value = "/update", 
		produces = "application/json"
	)
	public @ResponseBody ResponseDto updateTemplate(
		@RequestHeader(value="AppID") String appId,
		@RequestHeader(value="Token") String token,
		@RequestHeader(value="SapId") Long sapId,
		@RequestHeader(value="DeviceType") String deviceType,
		@RequestBody @Valid TemplateRequestDto request, 
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
			
			this.templateService.updateTemplate(request, token, sapId, deviceType);
		}
		catch(ServiceException ex) {
			return ResponseDto.newError(transaction, ex.getMessage());
		}
		
		return ResponseDto.newSuccess(transaction, null);
	}


	@Authorize("TEMPLA")
	@RequestMapping(
		method = RequestMethod.POST, 
		value = "/delete", 
		produces = "application/json"
	)
	public @ResponseBody ResponseDto deleteTemplate(
		@RequestHeader(value="AppID") String appId,
		@RequestHeader(value="Token") String token,
		@RequestHeader(value="SapId") Long sapId,
		@RequestHeader(value="DeviceType") String deviceType,
		@RequestBody @Valid TemplateRequestDto request, 
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
			
			this.templateService.deleteTemplate(request, token, sapId, deviceType);
		}
		catch(ServiceException ex) {
			return ResponseDto.newError(transaction, ex.getMessage());
		}
		
		return ResponseDto.newSuccess(transaction, null);
	}
}

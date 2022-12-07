package ar.com.scienza.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.scienza.base.Authorize;
import ar.com.scienza.dto.BodyDto;
import ar.com.scienza.dto.EntityResponseDto;
import ar.com.scienza.dto.QueryTypeResponseDto;
import ar.com.scienza.dto.ResponseListDto;
import ar.com.scienza.entity.Transaction;
import ar.com.scienza.exception.ServiceException;
import ar.com.scienza.service.IEntityService;


@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/entity")
public class EntityController {

	@Autowired
	private IEntityService entityService;

	@Autowired
	private Transaction transaction;

	
	@Authorize("ENTITY")
	@RequestMapping(
		method = RequestMethod.GET, 
		value = "/cellCompanyList", 
		produces = "application/json"
	)
	public @ResponseBody ResponseListDto getCellCompanyList(
		@RequestHeader(value="AppID") String appId,
		@RequestHeader(value="Token") String token,
		@RequestHeader(value="SapId") Long sapId,
		@RequestHeader(value="DeviceType") String deviceType
	) {

		List<BodyDto> responseList = new ArrayList<BodyDto>();
		
		try
		{		
			List<EntityResponseDto> list = this.entityService.getCellCompanyList(token, sapId, deviceType);
			responseList.addAll(list);
		}
		catch(ServiceException ex) {
			return ResponseListDto.newError(transaction, ex.getMessage());
		}
		
		return ResponseListDto.newSuccess(transaction, responseList);
	}

	
	@Authorize("ENTITY")
	@RequestMapping(
		method = RequestMethod.GET, 
		value = "/healthInsuranceList", 
		produces = "application/json"
	)
	public @ResponseBody ResponseListDto getHealthInsuranceList(
		@RequestHeader(value="AppID") String appId,
		@RequestHeader(value="Token") String token,
		@RequestHeader(value="SapId") Long sapId,
		@RequestHeader(value="DeviceType") String deviceType
	) {

		List<BodyDto> responseList = new ArrayList<BodyDto>();
		
		try
		{		
			List<EntityResponseDto> list = this.entityService.getHealthInsuranceList(token, sapId, deviceType);
			responseList.addAll(list);
		}
		catch(ServiceException ex) {
			return ResponseListDto.newError(transaction, ex.getMessage());
		}
		
		return ResponseListDto.newSuccess(transaction, responseList);
	}

	
	@Authorize("ENTITY")
	@RequestMapping(
		method = RequestMethod.GET, 
		value = "/locationProvinceList", 
		produces = "application/json"
	)
	public @ResponseBody ResponseListDto getLocationList(
		@RequestHeader(value="AppID") String appId,
		@RequestHeader(value="Token") String token,
		@RequestHeader(value="SapId") Long sapId,
		@RequestHeader(value="DeviceType") String deviceType
	) {

		List<BodyDto> responseList = new ArrayList<BodyDto>();
		
		try
		{		
			List<EntityResponseDto> list = this.entityService.getLocationList(token, sapId, deviceType);
			responseList.addAll(list);
		}
		catch(ServiceException ex) {
			return ResponseListDto.newError(transaction, ex.getMessage());
		}
		
		return ResponseListDto.newSuccess(transaction, responseList);
	}

	
	@Authorize("ENTITY")
	@RequestMapping(
		method = RequestMethod.GET, 
		value = "/queryTypeList", 
		produces = "application/json"
	)
	public @ResponseBody ResponseListDto getQueryTypeList(
		@RequestHeader(value="AppID") String appId,
		@RequestHeader(value="Token") String token,
		@RequestHeader(value="SapId") Long sapId,
		@RequestHeader(value="DeviceType") String deviceType
	) {

		List<BodyDto> responseList = new ArrayList<BodyDto>();
		
		try
		{		
			List<QueryTypeResponseDto> list = this.entityService.getQueryTypeList(token, sapId, deviceType);
			responseList.addAll(list);
		}
		catch(ServiceException ex) {
			return ResponseListDto.newError(transaction, ex.getMessage());
		}
		
		return ResponseListDto.newSuccess(transaction, responseList);
	}

	@Authorize("ENTITY")
	@RequestMapping(
		method = RequestMethod.GET,
		value = "/documentationTypes",
		produces = "application/json"
	)
	public @ResponseBody ResponseListDto documentationTypes(
		@RequestHeader(value="AppID") String appId,
		@RequestHeader(value="Token") String token,
		@RequestHeader(value="SapId") Long sapId,
		@RequestHeader(value="DeviceType") String deviceType
	) {

		List<BodyDto> responseList = new ArrayList<BodyDto>();

		try
		{
			List<EntityResponseDto> list = this.entityService.getDocumentationTypeList(token, sapId, deviceType);
			responseList.addAll(list);
		}
		catch(ServiceException ex) {
			return ResponseListDto.newError(transaction, ex.getMessage());
		}

		return ResponseListDto.newSuccess(transaction, responseList);
	}
}

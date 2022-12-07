package ar.com.scienza.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import ar.com.scienza.dto.*;
import org.apache.log4j.Logger;
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
import ar.com.scienza.service.IOrderService;


@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/order")
public class OrderController {
	
	final static Logger logger = Logger.getLogger(OrderController.class);
	
	@Autowired
	private IOrderService orderService;
	
	@Autowired
	private Transaction transaction;
		
	
	@Authorize("PEDAFI")
	@RequestMapping(
		method = RequestMethod.GET, 
		value = "/list", 
		produces = "application/json"
	)
	public @ResponseBody ResponseListDto getOrderList(
		@RequestHeader(value="AppID") String appId,
		@RequestHeader(value="Token") String token,
		@RequestHeader(value="SapId") Long sapId,
		@RequestHeader(value="DeviceType") String deviceType,
		@RequestHeader(value="Version", required = false) String version,
		@RequestParam(name="idOrder", required=false) Integer orderId
	) {

		List<BodyDto> responseList = new ArrayList<BodyDto>();
		
		try
		{		
			List<OrderResultResponseDto> list = this.orderService.getOrderList(orderId, token, sapId, deviceType, version);
			responseList.addAll(list);
		}
		catch(ServiceException ex) {
			return ResponseListDto.newError(transaction, ex.getMessage());
		}
		
		return ResponseListDto.newSuccess(transaction, responseList);
	}

	
	@Authorize("PEDAFI")
	@RequestMapping(
		method = RequestMethod.GET, 
		value = "/get", 
		produces = "application/json"
	)
	public @ResponseBody ResponseDto getOrder(
		@RequestHeader(value="AppID") String appId,
		@RequestHeader(value="Token") String token,
		@RequestHeader(value="SapId") Long sapId,
		@RequestHeader(value="DeviceType") String deviceType,
		@RequestHeader(value="Version", required = false) String version,
		@RequestParam(name="idOrder") Integer orderId
	) {

		OrderResponseDto response = null;
		
		try {

			response = this.orderService.getOrder(orderId, token, sapId, deviceType, version);

		}
		catch(ServiceException ex) {
			return ResponseDto.newError(transaction, ex.getMessage());
		}
		
		return ResponseDto.newSuccess(transaction, response);
	}

	
	@Authorize("PEDCOR")
	@RequestMapping(
			method = RequestMethod.GET,
			value = "/coordination",
			produces = "application/json"
	)
	public @ResponseBody ResponseDto getCoordination(
			@RequestHeader(value="AppID") String appId,
			@RequestHeader(value="Token") String token,
			@RequestHeader(value="SapId") Long sapId,
			@RequestHeader(value="DeviceType") String deviceType,
			@RequestParam(name="idOrder") Integer orderId
	){

		CoordinationResponseDto response = null;

		try {
			response = this.orderService.getCoordination(sapId, orderId, token, deviceType);
		}
		catch(ServiceException ex) {
			return ResponseDto.newError(transaction, ex.getMessage());
		}

		return ResponseDto.newSuccess(transaction, response);
	}

	
	@Authorize("PEDCOR")
	@RequestMapping(
		method = RequestMethod.POST, 
		value = "/coordination",
		produces = "application/json"
	)
	public @ResponseBody ResponseDto coordinateDelivery(
		@RequestHeader(value="AppID") String appId,
		@RequestHeader(value="Token") String token,
		@RequestHeader(value="SapId") Long sapId,
		@RequestHeader(value="DeviceType") String deviceType,
		@RequestBody @Valid CoordinationRequestDto request,
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
			
			this.orderService.coordinateDelivery(request, token, sapId, deviceType);
		}
		catch(ServiceException ex) {
			return ResponseDto.newError(transaction, ex.getMessage());
		}
		
		return ResponseDto.newSuccess(transaction, null);
	}


	@Authorize("PEDAFI")
	@RequestMapping(
		method = RequestMethod.GET, 
		value = "/deliveryLocation", 
		produces = "application/json"
	)
	public @ResponseBody ResponseDto getDeliveryLocation(
		@RequestHeader(value="AppID") String appId,
		@RequestHeader(value="Token") String token,
		@RequestHeader(value="SapId") Long sapId,
		@RequestHeader(value="DeviceType") String deviceType,
		@RequestParam(name="deliveryNumber") Integer deliveryNumber
	) {

		DeliveryLocationResponseDto response = null;
		
		try
		{	
			response = this.orderService.getDeliveryLocation(deliveryNumber, token, sapId, deviceType);
		}
		catch(ServiceException ex) {
			return ResponseDto.newError(transaction, ex.getMessage());
		}
		
		return ResponseDto.newSuccess(transaction, response);
	}

	@Authorize("BUSDIS")
	@RequestMapping(
			method = RequestMethod.GET,
			value = "/pharmacySearch",
			produces = "application/json"
	)
	public @ResponseBody ResponseListDto getPharmacySearch(
			@RequestHeader(value="AppID") String appId,
			@RequestHeader(value="Token") String token,
			@RequestHeader(value="SapId") Long sapId,
			@RequestHeader(value="DeviceType") String deviceType,
			@RequestParam(name="idOrder") Integer idOrder

	) {

		List<BodyDto> response = new ArrayList<>();

		try
		{
			List<PharmacyResponseDto> pharmacies = this.orderService.getPharmacySearch(idOrder, token, sapId, deviceType);
			response.addAll(pharmacies);
		}
		catch(ServiceException ex) {
			 return ResponseListDto.newError(transaction, ex.getMessage());
		}

		return ResponseListDto.newSuccess(transaction, response);
	}


}

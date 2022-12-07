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
import ar.com.scienza.service.INotificationService;


@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/notification")
public class NotificationController {

	
	@Autowired
	private INotificationService notificationService;
	
	@Autowired
	private Transaction transaction;
	
	
	@Authorize("NOTAFI")
	@RequestMapping(
		method = RequestMethod.GET, 
		value = "/list", 
		produces = "application/json"
	)
	public @ResponseBody ResponseListDto getNotificationList(
		@RequestHeader(value="AppID") String appId,
		@RequestHeader(value="Token") String token,
		@RequestHeader(value="SapId") Long sapId,
		@RequestHeader(value="DeviceType") String deviceType
	) {

		List<BodyDto> responseList = new ArrayList<BodyDto>();
		
		try
		{		
			List<NotificationResultResponseDto> list = this.notificationService.getNotificationList(token, sapId, deviceType);
			responseList.addAll(list);
		}
		catch(ServiceException ex) {
			return ResponseListDto.newError(transaction, ex.getMessage());
		}
		
		return ResponseListDto.newSuccess(transaction, responseList);
	}

	
	@Authorize("NOTAFI")
	@RequestMapping(
		method = RequestMethod.GET, 
		value = "/get", 
		produces = "application/json"
	)
	public @ResponseBody ResponseDto getNotification(
		@RequestHeader(value="AppID") String appId,
		@RequestHeader(value="Token") String token,
		@RequestHeader(value="SapId") Long sapId,
		@RequestHeader(value="DeviceType") String deviceType,
		@RequestHeader(value="Version", required = false) String version,
		@RequestParam(name="idNotification") Integer notificationId
	) {

		NotificationResponseDto response = null;
		
		try
		{		
			response = this.notificationService.getNotification(notificationId, token, sapId, deviceType, version);
		}
		catch(ServiceException ex) {
			return ResponseDto.newError(transaction, ex.getMessage());
		}
		
		return ResponseDto.newSuccess(transaction, response);
	}

	@Authorize("NOTAFI")
	@RequestMapping(
		method = RequestMethod.POST,
		value = "/delete",
		produces = "application/json"
	)
	public @ResponseBody ResponseDto deleteNotification(
		@RequestHeader(value="AppID") String appId,
		@RequestHeader(value="Token") String token,
		@RequestHeader(value="SapId") Long sapId,
		@RequestHeader(value="DeviceType") String deviceType,
		@RequestBody @Valid NotificationDeleteRequestDto request,
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

			notificationService.deleteNotification(request, token, sapId, deviceType);
		}
		catch(ServiceException ex) {
			return ResponseDto.newError(transaction, ex.getMessage());
		}

		return ResponseDto.newSuccess(transaction, null);
	}

	
	@Authorize("ENVMEN")
	@RequestMapping(
		method = RequestMethod.POST, 
		value = "/senders/list", 
		produces = "application/json"
	)
	public @ResponseBody ResponseListDto getNotificationList(
		@RequestHeader(value="AppID") String appId,
		@RequestHeader(value="Token") String token,
		@RequestHeader(value="SapId") Long sapId,
		@RequestHeader(value="DeviceType") String deviceType,
		@RequestBody @Valid NotificationMessageFilterRequestDto request, 
		BindingResult result, 
		Model model
	) {

		List<BodyDto> responseList = new ArrayList<BodyDto>();
		
		try
		{
			ScienzaLogger.logRequest(request, transaction);
			
			if(result.hasErrors()) {
				ObjectError error = result.getAllErrors().get(0);
				return ResponseListDto.newError(transaction, error.getDefaultMessage());
			}
			
			List<NotificationMessageResultResponseDto> list = this.notificationService.getMessageSenderList(request, token, sapId, deviceType);
			responseList.addAll(list);
		}
		catch(ServiceException ex) {
			return ResponseListDto.newError(transaction, ex.getMessage());
		}
		
		return ResponseListDto.newSuccess(transaction, responseList);
	}

	
	@Authorize("ENVMEN")
	@RequestMapping(
		method = RequestMethod.POST, 
		value = "/senders/send", 
		produces = "application/json"
	)
	public @ResponseBody ResponseDto sendNotification(
		@RequestHeader(value="AppID") String appId,
		@RequestHeader(value="Token") String token,
		@RequestHeader(value="SapId") Long sapId,
		@RequestHeader(value="DeviceType") String deviceType,
		@RequestBody @Valid NotificationMessageSendRequestDto request, 
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
			
			this.notificationService.sendNotification(request, token, sapId, deviceType);
		}
		catch(ServiceException ex) {
			return ResponseDto.newError(transaction, ex.getMessage());
		}

		return ResponseDto.newSuccess(transaction, null);
	}

	@Authorize("HISMEN")
	@RequestMapping(
			method = RequestMethod.GET,
			value = "/sentNotificationsHistory",
			produces = "application/json"
	)
	public @ResponseBody ResponseListDto getSentNotificationsHistory(
			@RequestHeader(value="AppID") String appId,
			@RequestHeader(value="Token") String token,
			@RequestHeader(value="SapId") Long sapId,
			@RequestHeader(value="DeviceType") String deviceType,
			@RequestHeader(value="Version", required = false) String version
	) {

		List<BodyDto> responseList = new ArrayList<BodyDto>();

		try
		{
			List<NotificationMessageSentListDto> list = notificationService.getSentNotificationsHistory(token, sapId, deviceType);
			responseList.addAll(list);

		}catch(ServiceException ex)
		{
			return ResponseListDto.newError(transaction, ex.getMessage());
		}

		return ResponseListDto.newSuccess(transaction, responseList);

	}

	@Authorize("HISMEN")
	@RequestMapping(
			method = RequestMethod.GET,
			value = "/sentNotificationDetail",
			produces = "application/json"
	)
	public @ResponseBody ResponseDto getSentNotificationDetail(
			@RequestHeader(value="AppID") String appId,
			@RequestHeader(value="Token") String token,
			@RequestHeader(value="SapId") Long sapId,
			@RequestHeader(value="DeviceType") String deviceType,
			@RequestHeader(value="Version", required = false) String version,
			@RequestParam(name="idMessage") Integer idMessage
	) {

		NotificationMessageSentDto response = null;

		try
		{
			response = this.notificationService.getSentNotificationDetail(idMessage, token, sapId, deviceType, version);

		}catch (ServiceException ex)
		{
			return ResponseDto.newError(transaction, ex.getMessage());
		}

		return ResponseDto.newSuccess(transaction, response);
	}
}

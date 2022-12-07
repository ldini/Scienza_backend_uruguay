package ar.com.scienza.controller;

import javax.validation.Valid;

import ar.com.scienza.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import ar.com.scienza.base.Authorize;
import ar.com.scienza.config.ScienzaLogger;
import ar.com.scienza.entity.Transaction;
import ar.com.scienza.enumerator.TipoDocumentacionEnum;
import ar.com.scienza.exception.ServiceException;
import ar.com.scienza.service.IDocumentationService;

import java.util.ArrayList;
import java.util.List;


@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/documentation")
public class DocumentationController {

	
	@Autowired
	private IDocumentationService documentationService;
	
	@Autowired
	private Transaction transaction;
	
	// Lista
	@Authorize("CONAFI")
	@RequestMapping(
			method = RequestMethod.GET,
			value = "/",
			produces = "application/json"
	)
	public @ResponseBody
	ResponseListDto getDocumentationList(
			@RequestHeader(value="AppID") String appId,
			@RequestHeader(value="Token") String token,
			@RequestHeader(value="SapId") Long sapId,
			@RequestHeader(value="DeviceType") String deviceType
	) {

		List<BodyDto> responseList = new ArrayList<>();

		try
		{
			List<DocumentationResultRespDto> list = documentationService.getDocumentationList(TipoDocumentacionEnum.RECETA_MEDICA , token, sapId, deviceType);
			responseList.addAll(list);
		}
		catch(ServiceException ex) {
			return ResponseListDto.newError(transaction, ex.getMessage());
		}

		return ResponseListDto.newSuccess(transaction, responseList);
	}


	// Detalle
	@Authorize("CONAFI")
	@RequestMapping(
			method = RequestMethod.GET,
			value = "/{id}",
			produces = "application/json"
	)
	public @ResponseBody
	ResponseDto getDocumentation(
			@RequestHeader(value="AppID") String appId,
			@RequestHeader(value="Token") String token,
			@RequestHeader(value="SapId") Long sapId,
			@RequestHeader(value="DeviceType") String deviceType,
			@PathVariable(name="id") Integer documentationId
	) {

		DocumentationResponseDto response = null;

		try
		{
			response = documentationService.getDocumentation(documentationId, token, sapId, deviceType);
		}

		catch (ServiceException ex) {
			return ResponseDto.newError(transaction, ex.getMessage());
		}

		return ResponseDto.newSuccess(transaction, response);
	}

	// Alta
	@Authorize("CONAFI")
	@RequestMapping(
			method = RequestMethod.POST,
			value = "/",
			produces = "application/json"
	)
	public @ResponseBody ResponseDto createDocumentation(
			@RequestHeader(value="AppID") String appId,
			@RequestHeader(value="Token") String token,
			@RequestHeader(value="SapId") Long sapId,
			@RequestHeader(value="DeviceType") String deviceType,
			@RequestBody @Valid DocumentationCreateRequestDto request,
			BindingResult result,
			Model model
	) {

		DocumentationCreateResponseDto response = null;

		try
		{
			ScienzaLogger.logRequest(request, transaction);

			if(result.hasErrors()) {
				ObjectError error = result.getAllErrors().get(0);
				return ResponseDto.newError(transaction, error.getDefaultMessage());
			}

			response = documentationService.createDocumentation(request, token, sapId, deviceType);
		}
		catch(ServiceException ex) {
			return ResponseDto.newError(transaction, ex.getMessage());
		}

		return ResponseDto.newSuccess(transaction, response);
	}
}

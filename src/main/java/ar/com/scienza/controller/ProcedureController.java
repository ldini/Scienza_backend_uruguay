package ar.com.scienza.controller;

import ar.com.scienza.base.Authorize;
import ar.com.scienza.config.ScienzaLogger;
import ar.com.scienza.dto.*;
import ar.com.scienza.entity.Transaction;
import ar.com.scienza.exception.ServiceException;
import ar.com.scienza.service.IProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/procedure")
public class ProcedureController {

    @Autowired
    private IProcedureService procedureService;

    @Autowired
    private Transaction transaction;


    @Authorize("CONAFI")
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/",
            produces = "application/json"
    )
    public @ResponseBody
    ResponseListDto getProcedureList(
            @RequestHeader(value="AppID") String appId,
            @RequestHeader(value="Token") String token,
            @RequestHeader(value="SapId") Long sapId,
            @RequestHeader(value="DeviceType") String deviceType
    ) {

        List<BodyDto> responseList = new ArrayList<>();

        try
        {
            List<ProcedureResultResponseDto> list = procedureService.getProcedureList(token, sapId, deviceType);
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
            value = "/{id}",
            produces = "application/json"
    )
    public @ResponseBody
    ResponseDto getProcedure(
            @RequestHeader(value="AppID") String appId,
            @RequestHeader(value="Token") String token,
            @RequestHeader(value="SapId") Long sapId,
            @RequestHeader(value="DeviceType") String deviceType,
            @PathVariable(name="id") Integer tramiteId
    ) {

        ProcedureResponseDto response = null;

        try
        {
            response = procedureService.getProcedure(tramiteId, token, sapId, deviceType);
        }
        catch(ServiceException ex) {
            return ResponseDto.newError(transaction, ex.getMessage());
        }

        return ResponseDto.newSuccess(transaction, response);
    }


    @Authorize("CONAFI")
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/",
            produces = "application/json"
    )
    public @ResponseBody ResponseDto createProcedure(
            @RequestHeader(value="AppID") String appId,
            @RequestHeader(value="Token") String token,
            @RequestHeader(value="SapId") Long sapId,
            @RequestHeader(value="DeviceType") String deviceType,
            @RequestBody @Valid ProcedureCreateRequestDto request,
            BindingResult result,
            Model model
    ) {

        ProcedureCreateResponseDto response = null;

        try
        {
            ScienzaLogger.logRequest(request, transaction);

            if(result.hasErrors()) {
                ObjectError error = result.getAllErrors().get(0);
                return ResponseDto.newError(transaction, error.getDefaultMessage());
            }

            response = procedureService.createProcedure(request, token, sapId, deviceType);
        }
        catch(ServiceException ex) {
            return ResponseDto.newError(transaction, ex.getMessage());
        }

        return ResponseDto.newSuccess(transaction, response);
    }
}

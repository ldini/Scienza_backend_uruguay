package ar.com.scienza.controller;

import java.util.ArrayList;
import java.util.List;

import ar.com.scienza.dto.ReportActionsAffiliateResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import ar.com.scienza.base.Authorize;
import ar.com.scienza.dto.BodyDto;
import ar.com.scienza.dto.ReportUniverseAffiliateResponseDto;
import ar.com.scienza.dto.ResponseListDto;
import ar.com.scienza.entity.Transaction;
import ar.com.scienza.exception.ServiceException;
import ar.com.scienza.service.IReportService;


@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/report")
public class ReportController {

	@Autowired
	private IReportService reportService;

	@Autowired
	private Transaction transaction;

	
	@Authorize("REPADM")
	@RequestMapping(
		method = RequestMethod.GET, 
		value = "/universeAffiliateList", 
		produces = "application/json"
	)
	public @ResponseBody ResponseListDto universeAffiliate(
		@RequestHeader(value="AppID") String appId,
		@RequestHeader(value="Token") String token,
		@RequestHeader(value="SapId") Long sapId,
		@RequestHeader(value="DeviceType") String deviceType
	) {

		List<BodyDto> responseList = new ArrayList<BodyDto>();
		
		try
		{		
			List<ReportUniverseAffiliateResponseDto> list = this.reportService.getUniverseAffiliateList(token, sapId, deviceType);
			responseList.addAll(list);
		}
		catch(ServiceException ex) {
			return ResponseListDto.newError(transaction, ex.getMessage());
		}
		
		return ResponseListDto.newSuccess(transaction, responseList);
	}

	@Authorize("REPADM")
	@RequestMapping(
			method = RequestMethod.GET,
			value = "/actionsAffiliateList",
			produces = "application/json"
	)
	public @ResponseBody ResponseListDto actionsAffiliate(
			@RequestHeader(value="AppID") String appId,
			@RequestHeader(value="Token") String token,
			@RequestHeader(value="SapId") Long sapId,
			@RequestHeader(value="DeviceType") String deviceType,
			@RequestParam(name="startDate") String startDate,
			@RequestParam(name="finishDate") String finishDate
	) {

		List<BodyDto> responseList = new ArrayList<BodyDto>();

		try
		{
			List<ReportActionsAffiliateResponseDto> list = this.reportService.getActionsAffiliateList(token, sapId, deviceType, startDate, finishDate);
			responseList.addAll(list);
		}
		catch(ServiceException ex) {
			return ResponseListDto.newError(transaction, ex.getMessage());
		}

		return ResponseListDto.newSuccess(transaction, responseList);
	}
}

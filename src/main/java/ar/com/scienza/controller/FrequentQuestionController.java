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
import ar.com.scienza.dto.FrequentQuestionResponseDto;
import ar.com.scienza.dto.ResponseListDto;
import ar.com.scienza.entity.Transaction;
import ar.com.scienza.exception.ServiceException;
import ar.com.scienza.service.IFrequentQuestionlService;


@RestController
@EnableAutoConfiguration
public class FrequentQuestionController {

	@Autowired
	private IFrequentQuestionlService frequentQuestionService;

	@Autowired
	private Transaction transaction;

	
	@Authorize("*")
	@RequestMapping(
		method = RequestMethod.GET, 
		value = "/frequent-questions", 
		produces = "application/json"
	)
	public @ResponseBody ResponseListDto getFrequentQuestionList(
		@RequestHeader(value="AppID") String appId,
		@RequestHeader(value="Token") String token,
		@RequestHeader(value="SapId") Long sapId,
		@RequestHeader(value="DeviceType") String deviceType
	) {

		List<BodyDto> responseList = new ArrayList<BodyDto>();
		
		try
		{		
			List<FrequentQuestionResponseDto> list = this.frequentQuestionService.getFrequentQuestionList(token, sapId, deviceType);
			responseList.addAll(list);
		}
		catch(ServiceException ex) {
			return ResponseListDto.newError(transaction, ex.getMessage());
		}
		
		return ResponseListDto.newSuccess(transaction, responseList);
	}
}

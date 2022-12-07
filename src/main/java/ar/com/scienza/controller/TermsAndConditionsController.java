package ar.com.scienza.controller;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.scienza.base.Authorize;
import ar.com.scienza.config.ScienzaLogger;
import ar.com.scienza.dto.ResponseDto;
import ar.com.scienza.dto.TermsAndConditionsResponseDto;
import ar.com.scienza.entity.Transaction;


@RestController
@EnableAutoConfiguration
public class TermsAndConditionsController {

	@Value("${location.files}")
    String locationFiles;

	@Value("${path.docs}")
    String pathDocs;
	
	@Autowired
	private Transaction transaction;
	
	
	@Authorize("*")
	@RequestMapping(
		method = RequestMethod.GET, 
		value = "/terms-and-conditions",
		produces = "application/json"
	)
	public @ResponseBody ResponseDto getTermsAndConditions(
		@RequestHeader(value="AppID") String appId,
		@RequestHeader(value="Token") String token,
		@RequestHeader(value="SapId") Long sapId,
		@RequestHeader(value="DeviceType") String deviceType
	) {

		TermsAndConditionsResponseDto response = null;
		
		try
		{		
			String html = new String(Files.readAllBytes(Paths.get(locationFiles + pathDocs + "terminos-y-condiciones-uy.html")),"UTF-8");
			
			response = new TermsAndConditionsResponseDto();
			response.setHtml(html);
		}
		catch(Exception ex) {
			ScienzaLogger.logError(ex);
			return ResponseDto.newError(transaction, ex.getMessage());
		}
		
		return ResponseDto.newSuccess(transaction, response);
	}
}

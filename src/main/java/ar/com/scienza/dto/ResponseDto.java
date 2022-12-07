package ar.com.scienza.dto;

import ar.com.scienza.config.ScienzaLogger;
import ar.com.scienza.entity.Transaction;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseDto {

	@JsonProperty("header")
	HeaderDto header;

	@JsonProperty("body")
	BodyDto body;

	
	public static ResponseDto newSuccess(Transaction transaction, BodyDto body) {
		ResponseDto response = new ResponseDto();
		response.setHeader(new HeaderDto(0, null));
		response.setBody(body);
		ScienzaLogger.logResponse(response, transaction);
		return response;
	}
	
	public static ResponseDto newError(Transaction transaction, String message) {
		ResponseDto response = new ResponseDto();
		response.setHeader(new HeaderDto(1, message));
		response.setBody(null);
		ScienzaLogger.logResponse(response, transaction);
		return response;
	}
	
	public static ResponseDto newCustomError(Transaction transaction, Integer code, String message) {
		ResponseDto response = new ResponseDto();
		response.setHeader(new HeaderDto(code, message));
		response.setBody(null);
		ScienzaLogger.logResponse(response, transaction);
		return response;
	}
	
	
	public HeaderDto getHeader() {
		return header;
	}

	public void setHeader(HeaderDto header) {
		this.header = header;
	}

	public BodyDto getBody() {
		return body;
	}

	public void setBody(BodyDto body) {
		this.body = body;
	}
}

package ar.com.scienza.dto;

import java.util.List;

import ar.com.scienza.config.ScienzaLogger;
import ar.com.scienza.entity.Transaction;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "header", "bodyList" })
public class ResponseListDto {

	@JsonProperty("header")
	HeaderDto header;

	@JsonProperty("body")
	List<BodyDto> bodyList;

	
	public static ResponseListDto newSuccess(Transaction transaction, List<BodyDto> bodyList) {
		ResponseListDto response = new ResponseListDto();
		response.setHeader(new HeaderDto(0, null));
		response.setBody(bodyList);
		ScienzaLogger.logResponse(response, transaction);
		return response;
	}
	
	public static ResponseListDto newError(Transaction transaction, String message) {
		ResponseListDto response = new ResponseListDto();
		response.setHeader(new HeaderDto(1, message));
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

	public List<BodyDto> getBody() {
		return bodyList;
	}

	public void setBody(List<BodyDto> bodyList) {
		this.bodyList = bodyList;
	}
}

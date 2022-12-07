package ar.com.scienza.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegistrationErrorResponseDto extends BodyDto {

	@JsonProperty("message")
	private String message;

	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}

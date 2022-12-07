package ar.com.scienza.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HeaderDto {

	@JsonProperty("code")
	private Integer code;

	@JsonProperty("error")
	private String error;

	
	public HeaderDto(Integer code, String error) {
		this.code = code;
		this.error = error;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}

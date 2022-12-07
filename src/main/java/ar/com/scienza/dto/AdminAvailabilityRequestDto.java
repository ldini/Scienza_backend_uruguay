package ar.com.scienza.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AdminAvailabilityRequestDto {

	@NotNull(message="available {required}")
	@JsonProperty("available")
	private Boolean available;

	
	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}
}

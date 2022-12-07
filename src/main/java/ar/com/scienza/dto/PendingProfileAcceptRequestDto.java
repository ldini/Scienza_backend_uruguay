package ar.com.scienza.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PendingProfileAcceptRequestDto extends BodyDto {

	@NotNull(message="idResult {required}")
	@JsonProperty("idResult")
	private Integer bandejaId;

	
	public Integer getBandejaId() {
		return bandejaId;
	}

	public void setBandejaId(Integer bandejaId) {
		this.bandejaId = bandejaId;
	}
}

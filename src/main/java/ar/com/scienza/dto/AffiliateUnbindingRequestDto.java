package ar.com.scienza.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AffiliateUnbindingRequestDto {

	@NotNull(message="sapId {required}")
	@Max(message="sapId {max_value}", value=999999999)
	@JsonProperty("sapId")
	private Long sapId;


	public Long getSapId() {
		return sapId;
	}

	public void setSapId(Long sapId) {
		this.sapId = sapId;
	}
}

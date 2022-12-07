package ar.com.scienza.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AffiliateBindingRequestDto {

	@NotNull(message="identification {required}")
	@JsonProperty("identification")
	private String cedulaIdentidad;

	@NotNull(message="sapId {required}")
	@Max(message="sapId {max_value}", value=999999999)
	@JsonProperty("sapId")
	private Long sapId;


	public String getcedulaIdentidad() {
		return cedulaIdentidad;
	}

	public void setcedulaIdentidad(String cedulaIdentidad) {
		this.cedulaIdentidad = cedulaIdentidad;
	}

	public Long getSapId() {
		return sapId;
	}

	public void setSapId(Long sapId) {
		this.sapId = sapId;
	}
}

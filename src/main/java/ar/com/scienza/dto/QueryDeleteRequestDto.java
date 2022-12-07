package ar.com.scienza.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QueryDeleteRequestDto {
	
	@NotNull(message="idQuery {required}")
	@JsonProperty("idQuery")
	private Integer consultaId;

	
	public Integer getConsultaId() {
		return consultaId;
	}

	public void setConsultaId(Integer consultaId) {
		this.consultaId = consultaId;
	}
}

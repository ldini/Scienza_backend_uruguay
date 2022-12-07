package ar.com.scienza.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AdminQueryCloseRequestDto extends BodyDto {

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

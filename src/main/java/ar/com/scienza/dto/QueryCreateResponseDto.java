package ar.com.scienza.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QueryCreateResponseDto extends BodyDto {

	@JsonProperty("idQuery")
	private Integer consultaId;

	
	public Integer getConsultaId() {
		return consultaId;
	}

	public void setConsultaId(Integer consultaId) {
		this.consultaId = consultaId;
	}
}

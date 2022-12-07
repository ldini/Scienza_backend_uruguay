package ar.com.scienza.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TraceDetailOriginResponseDto extends BodyDto {

	@JsonProperty("receipt")
	private String comprobante;
	
	@JsonProperty("dateTime")
	private String fechaHora;

	
	public String getComprobante() {
		return comprobante;
	}

	public void setComprobante(String comprobante) {
		this.comprobante = comprobante;
	}

	public String getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(String fechaHora) {
		this.fechaHora = fechaHora;
	}
}

package ar.com.scienza.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QueryMessageResponseDto extends BodyDto {

	@JsonProperty("from")
	private String emisor;

	@JsonProperty("message")
	private String mensaje;

	@JsonProperty("date")
	private String fecha;
	

	public String getEmisor() {
		return emisor;
	}

	public void setEmisor(String emisor) {
		this.emisor = emisor;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
}

package ar.com.scienza.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GoogleMapsCoordsResponseDto extends BodyDto {

	@JsonProperty("latitude")
	private String latitud;
	
	@JsonProperty("longitude")
	private String longitud;

	
	public String getLatitud() {
		return latitud;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	public String getLongitud() {
		return longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}
}

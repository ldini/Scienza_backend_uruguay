package ar.com.scienza.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GoogleMapsFullDataResponseDto extends BodyDto {

	@JsonProperty("province")
	private String provincia;

	@JsonProperty("locality")
	private String localidad;

	@JsonProperty("street")
	private String calle;

	@JsonProperty("streetNumber")
	private Integer calleNumero;

	@JsonProperty("postalCode")
	private String codigoPostal;

	@JsonProperty("latitude")
	private String latitud;
	
	@JsonProperty("longitude")
	private String longitud;

	
	public String getProvincia() {
		return provincia;
	}

	public void setDepartamento(String provincia) {
		this.provincia = provincia;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public Integer getCalleNumero() {
		return calleNumero;
	}

	public void setCalleNumero(Integer calleNumero) {
		this.calleNumero = calleNumero;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

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

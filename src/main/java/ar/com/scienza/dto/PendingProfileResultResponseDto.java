package ar.com.scienza.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PendingProfileResultResponseDto extends BodyDto {

	@JsonProperty("idResult")
	private Integer bandejaId;
	
	@JsonProperty("dateEdition")
	private String fechaEdicion;
	
	@JsonProperty("firstName")
	private String nombre;
	
	@JsonProperty("lastName")
	private String apellido;
	
	@JsonProperty("identification")
	private String cedulaIdentidad;

	@JsonProperty("sapId")
	private Long sapId;
	
	@JsonProperty("healthInsurance")
	private String seguroMedico;

	
	public Integer getBandejaId() {
		return bandejaId;
	}

	public void setBandejaId(Integer bandejaId) {
		this.bandejaId = bandejaId;
	}

	public String getFechaEdicion() {
		return fechaEdicion;
	}

	public void setFechaEdicion(String fechaEdicion) {
		this.fechaEdicion = fechaEdicion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

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

	public String getSeguroMedico() {
		return seguroMedico;
	}

	public void setSeguroMedico(String seguroMedico) {
		this.seguroMedico = seguroMedico;
	}
}

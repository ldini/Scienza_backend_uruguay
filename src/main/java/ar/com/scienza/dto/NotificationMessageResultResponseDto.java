package ar.com.scienza.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NotificationMessageResultResponseDto extends BodyDto {

	@JsonProperty("idAffiliate")
	private Integer afiliadoId;
	
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

	
	public Integer getAfiliadoId() {
		return afiliadoId;
	}

	public void setAfiliadoId(Integer afiliadoId) {
		this.afiliadoId = afiliadoId;
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

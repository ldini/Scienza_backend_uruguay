package ar.com.scienza.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PendingRegistrationResponseDto extends BodyDto {

	@JsonProperty("idResult")
	private Integer bandejaId;
	
	@JsonProperty("dateError")
	private String fechaError;
	
	@JsonProperty("firstName")
	private String nombre;
	
	@JsonProperty("lastName")
	private String apellido;
	
	@JsonProperty("identification")
	private String cedulaIdentidad;

	@JsonProperty("sapId")
	private Long sapId;
	
	@JsonProperty("telephone")
	private String telefono;
	
	@JsonProperty("email")
	private String email;

	@JsonProperty("state")
	private String estado;

	@JsonProperty("observations")
	private List<ObservacionAltaPendienteResponseDto> observaciones;

	
	public Integer getBandejaId() {
		return bandejaId;
	}

	public void setBandejaId(Integer bandejaId) {
		this.bandejaId = bandejaId;
	}

	public String getFechaError() {
		return fechaError;
	}

	public void setFechaError(String fechaError) {
		this.fechaError = fechaError;
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

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEstado() { return estado; }

	public void setEstado(String estado) { this.estado = estado; }

	public List<ObservacionAltaPendienteResponseDto> getObservaciones() { return observaciones; }

	public void setObservaciones(List<ObservacionAltaPendienteResponseDto> observaciones) { this.observaciones = observaciones; }
}

package ar.com.scienza.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReportUniverseAffiliateResponseDto extends BodyDto {

	@JsonProperty("registrationDate")
	private String fechaAlta;

	@JsonProperty("sapId")
	private Integer sapId;
	
	@JsonProperty("name")
	private String nombre;

	@JsonProperty("lastName")
	private String apellido;

	@JsonProperty("identification")
	private String cedulaIdentidad;
	
	@JsonProperty("affiliateNumber")
	private String numeroAfiliado;

	@JsonProperty("healthInsurance")
	private String seguroMedico;

	@JsonProperty("email")
	private String email;
	
	@JsonProperty("lastTransactionDate")
	private String fechaUltimaTransaccion;

	
	public String getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Integer getSapId() {
		return sapId;
	}

	public void setSapId(Integer sapId) {
		this.sapId = sapId;
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

	public String getNumeroAfiliado() {
		return numeroAfiliado;
	}

	public void setNumeroAfiliado(String numeroAfiliado) {
		this.numeroAfiliado = numeroAfiliado;
	}

	public String getSeguroMedico() {
		return seguroMedico;
	}

	public void setSeguroMedico(String seguroMedico) {
		this.seguroMedico = seguroMedico;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFechaUltimaTransaccion() {
		return fechaUltimaTransaccion;
	}

	public void setFechaUltimaTransaccion(String fechaUltimaTransaccion) {
		this.fechaUltimaTransaccion = fechaUltimaTransaccion;
	}
}

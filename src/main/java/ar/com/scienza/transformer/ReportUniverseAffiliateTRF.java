package ar.com.scienza.transformer;

import java.util.Date;


public class ReportUniverseAffiliateTRF {

	private Date fechaAlta;

	private Integer sapId;
	
	private String nombre;

	private String apellido;

	private String cedulaIdentidad;
	
	private String numeroAfiliado;

	private String seguroMedico;

	private String email;
	
	private Date fechaUltimaTransaccion;

	
	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
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

	public Date getFechaUltimaTransaccion() {
		return fechaUltimaTransaccion;
	}

	public void setFechaUltimaTransaccion(Date fechaUltimaTransaccion) {
		this.fechaUltimaTransaccion = fechaUltimaTransaccion;
	}
}

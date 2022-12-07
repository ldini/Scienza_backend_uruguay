package ar.com.scienza.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ar.com.scienza.base.ScienzaEntity;

@Entity
@Table(name="bandeja_alta_pendiente")
public class BandejaAltaPendiente extends ScienzaEntity {
	
	@Column(name="nombre", nullable = false, length = 50)
	private String nombre;
	
	@Column(name="apellido", nullable = false, length = 50)
	private String apellido;	

	@Column(name="numero_documento", nullable = false , length = 50)
	private String cedulaIdentidad;

	@Column(name="sap_id", nullable = false)
	private Long sapId;

	@Column(name="password", nullable = false)
	private String password;
	
	@Column(name="telefono", nullable = false, length = 50)
	private String telefono;

	@Column(name="email", nullable = false, length = 100)
	private String email;

	@Column(name = "tipo_dispositivo", nullable = false, length = 3)
	private String tipoDispositivo;

	@Column(name="estado", nullable = false, length = 1)
	private String estado;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_error", nullable = false)
    private Date fechaError;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_atencion", nullable = true)
    private Date fechaAtencion;	

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "administrador_id", nullable = true, referencedColumnName = "id")
	private Administrador administrador;

	
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getTipoDispositivo() {
		return tipoDispositivo;
	}

	public void setTipoDispositivo(String tipoDispositivo) {
		this.tipoDispositivo = tipoDispositivo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFechaError() {
		return fechaError;
	}

	public void setFechaError(Date fechaError) {
		this.fechaError = fechaError;
	}

	public Date getFechaAtencion() {
		return fechaAtencion;
	}

	public void setFechaAtencion(Date fechaAtencion) {
		this.fechaAtencion = fechaAtencion;
	}

	public Administrador getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}
}

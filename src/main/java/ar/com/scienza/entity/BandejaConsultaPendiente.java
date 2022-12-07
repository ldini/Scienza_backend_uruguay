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
@Table(name="bandeja_consulta_pendiente")
public class BandejaConsultaPendiente extends ScienzaEntity {

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "consulta_id", referencedColumnName = "id", nullable = false)
	private Consulta consulta;

	@Column(name="comentario", nullable = true, length = 500)
	private String comentario;

	@Column(name = "tipo_dispositivo", nullable = false, length = 3)
	private String tipoDispositivo;

	@Column(name="estado", nullable = false, length = 1)
	private String estado;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_consulta", nullable = false)
    private Date fechaConsulta;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_atencion", nullable = true)
    private Date fechaAtencion;	

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "administrador_id", nullable = true, referencedColumnName = "id")
	private Administrador administrador;

	
	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
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

	public Date getFechaConsulta() {
		return fechaConsulta;
	}

	public void setFechaConsulta(Date fechaConsulta) {
		this.fechaConsulta = fechaConsulta;
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

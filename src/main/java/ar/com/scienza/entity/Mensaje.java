package ar.com.scienza.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import ar.com.scienza.base.ScienzaEntity;

@Entity
@Table(name="mensaje")
public class Mensaje extends ScienzaEntity {
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "administrador_id", referencedColumnName = "id", nullable = true)
	private Administrador administrador;

	@Column(name="titulo", length = 100, nullable = false)
	private String titulo;

	@Column(name="mensaje_push", length = 100, nullable = false)
	private String mensajePush;

	@Column(name="mensaje_abreviado", length = 100, nullable = false)
	private String mensajeAbreviado;

	@Column(name="mensaje", nullable = false)
	@Type(type="text")
	private String mensaje;

	
	public Administrador getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getMensajePush() {
		return mensajePush;
	}

	public void setMensajePush(String mensajePush) {
		this.mensajePush = mensajePush;
	}

	public String getMensajeAbreviado() {
		return mensajeAbreviado;
	}

	public void setMensajeAbreviado(String mensajeAbreviado) {
		this.mensajeAbreviado = mensajeAbreviado;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
}

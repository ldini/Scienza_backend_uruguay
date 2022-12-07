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
@Table(name="template")
public class Template extends ScienzaEntity {
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "administrador_id", referencedColumnName = "id", nullable = false)
	private Administrador administrador;

	@Column(name="titulo", length = 100, nullable = false)
	private String titulo;

	@Column(name="descripcion", length = 200, nullable = false)
	private String descripcion;

	@Column(name="contenido", nullable = false)
	@Type(type="text")
	private String contenido;


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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
}

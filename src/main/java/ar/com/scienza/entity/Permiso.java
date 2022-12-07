package ar.com.scienza.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import ar.com.scienza.base.ScienzaEntity;

@Entity
@Table(name="permiso")
public class Permiso extends ScienzaEntity {

	@Column(name="nombre", nullable = false)
	private String nombre;
	
	@Column(name="descripcion", nullable = false)
	private String descripcion;

	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}

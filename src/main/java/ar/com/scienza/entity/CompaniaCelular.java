package ar.com.scienza.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import ar.com.scienza.base.ScienzaEntity;

@Entity
@Table(name="compania_celular")
public class CompaniaCelular extends ScienzaEntity {

	@Column(name="codigo", nullable = false, length = 5)
	private String codigo;
	
	@Column(name="descripcion", nullable = false, length = 50)
	private String descripcion;

	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}

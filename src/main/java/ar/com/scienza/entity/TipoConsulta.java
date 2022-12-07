package ar.com.scienza.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;

import ar.com.scienza.base.ScienzaEntity;

@Entity
@Table(name="tipo_consulta")
public class TipoConsulta extends ScienzaEntity {

	@Column(name="codigo", nullable = false, length = 6)
	private String codigo;
	
	@Column(name="descripcion", nullable = false, length = 50)
	private String descripcion;

	@OneToMany(
		fetch = FetchType.LAZY, 
		mappedBy = "tipoConsulta", 
		cascade = {
			CascadeType.DETACH,
	        CascadeType.MERGE,
	        CascadeType.REFRESH,
	        CascadeType.PERSIST	
		}
	)
	@Filter(name="tipo_categoria_filter")
	private List<TipoCategoria> tipoCategoriaList = new ArrayList<TipoCategoria>();
	
	
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

	public List<TipoCategoria> getTipoCategoriaList() {
		return tipoCategoriaList;
	}

	public void setTipoCategoriaList(List<TipoCategoria> tipoCategoriaList) {
		this.tipoCategoriaList = tipoCategoriaList;
	}
}

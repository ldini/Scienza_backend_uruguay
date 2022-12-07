package ar.com.scienza.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ar.com.scienza.base.ScienzaEntity;


@Entity
@Table(name="traza")
public class Traza extends ScienzaEntity {

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "entrega_material_id", referencedColumnName = "id", nullable = false)
	private EntregaMaterial entregaMaterial;
	
	@Column(name="codigo", nullable = false, length = 100)
	private String codigo;

	
	public EntregaMaterial getEntregaMaterial() {
		return entregaMaterial;
	}

	public void setEntregaMaterial(EntregaMaterial entregaMaterial) {
		this.entregaMaterial = entregaMaterial;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
}

package ar.com.scienza.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import ar.com.scienza.base.ScienzaEntity;

@Entity
@Table(name="seguro_medico")
public class SeguroMedico extends ScienzaEntity {

	@Column(name="sap_id", nullable = false)
	private Long sapId;
	
	@Column(name="sap_descripcion", nullable = false, length = 100)
	private String sapDescripcion;

	@Column(name="descripcion", nullable = false, length = 100)
	private String descripcion;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
		name = "seguro_medico_permiso", 
		joinColumns = { 
			@JoinColumn(name = "seguro_medico_id", referencedColumnName = "id", nullable = false, updatable = false) 
		}, 
		inverseJoinColumns = { 
			@JoinColumn(name = "permiso_id", referencedColumnName = "id", nullable = false, updatable = false) 
		}
	)
	private Set<Permiso> grants = new HashSet<Permiso>();

	
	public Long getSapId() {
		return sapId;
	}

	public void setSapId(Long sapId) {
		this.sapId = sapId;
	}

	public String getSapDescripcion() {
		return sapDescripcion;
	}

	public void setSapDescripcion(String sapDescripcion) {
		this.sapDescripcion = sapDescripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Set<Permiso> getGrants() {
		return grants;
	}

	public void setGrants(Set<Permiso> grants) {
		this.grants = grants;
	}
}

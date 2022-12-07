package ar.com.scienza.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

import ar.com.scienza.base.ScienzaEntity;

@Entity
@Table(name="rol")
public class Rol extends ScienzaEntity {

	@Column(name="nombre", nullable = false)
	private String nombre;
	
	@Column(name="descripcion", nullable = false)
	private String descripcion;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
		name = "rol_permiso", 
		joinColumns = { 
			@JoinColumn(name = "rol_id", referencedColumnName = "id", nullable = false, updatable = false) 
		}, 
		inverseJoinColumns = { 
			@JoinColumn(name = "permiso_id", referencedColumnName = "id", nullable = false, updatable = false) 
		}
	)
	private Set<Permiso> grants = new HashSet<Permiso>();
	
	
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
	public Set<Permiso> getGrants() {
		return grants;
	}
	public void setGrants(Set<Permiso> grants) {
		this.grants = grants;
	}
	
	/**
	 * Devuelve la lista de permisos ADMIN como String
	 * @return
	 */
	public List<String> getGrantsToString(Administrador admin) {
		return Lists.transform(
		    new ArrayList<Permiso>(this.getGrants()),
		    new Function<Permiso, String>() {
		        @Override
		        public String apply(Permiso permiso) {
		            return permiso.getNombre();
		        }
		    }
		);
	}

	/**
	 * Devuelve la lista de permisos AFILIADO como String
	 * @return
	 */
	public List<String> getGrantsToString(Afiliado afiliado) {
		
		List<Permiso> grants = new ArrayList<Permiso>();
		grants.addAll(this.getGrants());
		grants.addAll(afiliado.getSeguroMedico().getGrants());
		
		return Lists.transform(
			grants,
		    new Function<Permiso, String>() {
		        @Override
		        public String apply(Permiso permiso) {
		            return permiso.getNombre();
		        }
		    }
		);
	}
}

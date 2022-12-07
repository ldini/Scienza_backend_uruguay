package ar.com.scienza.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.FilterDef;

import ar.com.scienza.base.ScienzaEntity;

@Entity
@Table(name="consulta_sesion")
@FilterDef(
	name = "consulta_filter", 
	defaultCondition = "fecha_delete IS NULL"
)
public class ConsultaSesion extends ScienzaEntity { 

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "consulta_id", referencedColumnName = "id", nullable = false)
	private Consulta consulta;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "sesion_id", referencedColumnName = "id", nullable = false)
	private Sesion sesion;
	
	@Column(name="estado", length = 10, nullable = false)
	private String estado;

	
	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	public Sesion getSesion() {
		return sesion;
	}

	public void setSesion(Sesion sesion) {
		this.sesion = sesion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
}

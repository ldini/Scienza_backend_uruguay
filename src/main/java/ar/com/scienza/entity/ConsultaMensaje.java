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

import org.hibernate.annotations.FilterDef;

import ar.com.scienza.base.ScienzaEntity;

@Entity
@Table(name="consulta_mensaje")
@FilterDef(
	name = "consulta_filter", 
	defaultCondition = "fecha_delete IS NULL"
)
public class ConsultaMensaje extends ScienzaEntity { 

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "consulta_id", referencedColumnName = "id", nullable = false)
	private Consulta consulta;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "afiliado_id", referencedColumnName = "id", nullable = true)
	private Afiliado afiliado;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "administrador_id", referencedColumnName = "id", nullable = true)
	private Administrador administrador;

	@Column(name="mensaje", length = 500, nullable = false)
	private String mensaje;

	@Column(name="leido", columnDefinition ="BIT", length = 1, nullable = false)
	private Boolean leido;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_leido", nullable = true)
    private Date fechaLeido;

	
	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	public Afiliado getAfiliado() {
		return afiliado;
	}

	public void setAfiliado(Afiliado afiliado) {
		this.afiliado = afiliado;
	}

	public Administrador getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public Boolean getLeido() {
		return leido;
	}

	public void setLeido(Boolean leido) {
		this.leido = leido;
	}

	public Date getFechaLeido() {
		return fechaLeido;
	}

	public void setFechaLeido(Date fechaLeido) {
		this.fechaLeido = fechaLeido;
	}
}

package ar.com.scienza.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;

import ar.com.scienza.base.ScienzaEntity;

@Entity
@Table(name="consulta")
public class Consulta extends ScienzaEntity { 

	@Column(name="ticket", nullable = false)
	private Integer ticket;
	
	@Column(name="canal", nullable = false, length = 10)
	private String canal;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "afiliado_id", referencedColumnName = "id", nullable = false)
	private Afiliado afiliado;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "administrador_id", referencedColumnName = "id", nullable = true)
	private Administrador administrador;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tipo_categoria_id", referencedColumnName = "id", nullable = false)
	private TipoCategoria tipoCategoria;

	@OneToMany(
		fetch = FetchType.LAZY, 
		mappedBy = "consulta", 
		cascade = {
			CascadeType.DETACH,
	        CascadeType.MERGE,
	        CascadeType.REFRESH,
	        CascadeType.PERSIST	
		}
	)
	@Filter(name="consulta_filter")
	private List<ConsultaMensaje> consultaMensajeList = new ArrayList<ConsultaMensaje>();

	@OneToMany(
		fetch = FetchType.LAZY, 
		mappedBy = "consulta", 
		cascade = {
			CascadeType.DETACH,
	        CascadeType.MERGE,
	        CascadeType.REFRESH,
	        CascadeType.PERSIST	
		}
	)
	@Filter(name="consulta_filter")
	private List<ConsultaSesion> consultaSesionList = new ArrayList<ConsultaSesion>();

	
	public Integer getTicket() {
		return ticket;
	}

	public void setTicket(Integer ticket) {
		this.ticket = ticket;
	}

	public String getCanal() {
		return canal;
	}

	public void setCanal(String canal) {
		this.canal = canal;
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

	public TipoCategoria getTipoCategoria() {
		return tipoCategoria;
	}

	public void setTipoCategoria(TipoCategoria tipoCategoria) {
		this.tipoCategoria = tipoCategoria;
	}

	public List<ConsultaMensaje> getConsultaMensajeList() {
		return consultaMensajeList;
	}

	public void setConsultaMensajeList(List<ConsultaMensaje> consultaMensajeList) {
		this.consultaMensajeList = consultaMensajeList;
	}

	public List<ConsultaSesion> getConsultaSesionList() {
		return consultaSesionList;
	}

	public void setConsultaSesionList(List<ConsultaSesion> consultaSesionList) {
		this.consultaSesionList = consultaSesionList;
	}
}

package ar.com.scienza.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ar.com.scienza.base.ScienzaEntity;


@Entity
@Table(name="entrega_material")
public class EntregaMaterial extends ScienzaEntity { 

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "entrega_id", referencedColumnName = "id", nullable = false)
	private Entrega entrega;
	
	@Column(name="sap_id", nullable = false)
	private Long sapId;

	@Column(name="nombre", nullable = false, length = 100)
	private String nombre;

	@Column(name="cantidad", nullable = false)
	private Integer cantidad;

	@Column(name="monodroga", nullable = true, length = 100)
	private String monodroga;

	@Column(name="laboratorio", nullable = false, length = 100)
	private String laboratorio;

	@Column(name="lote", nullable = false, length = 100)
	private String lote;

	@Temporal(TemporalType.DATE)
	@Column(name="vencimiento", nullable = false)
	private Date vencimiento;

	@OneToMany(
		fetch = FetchType.LAZY, 
		mappedBy = "entregaMaterial", 
		cascade = {
			CascadeType.DETACH,
	        CascadeType.MERGE,
	        CascadeType.REFRESH,
	        CascadeType.PERSIST	
		}
	)
	private List<Traza> trazaList = new ArrayList<Traza>();
	
	
	public Entrega getEntrega() {
		return entrega;
	}

	public void setEntrega(Entrega entrega) {
		this.entrega = entrega;
	}

	public Long getSapId() {
		return sapId;
	}

	public void setSapId(Long sapId) {
		this.sapId = sapId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public String getMonodroga() {
		return monodroga;
	}

	public void setMonodroga(String monodroga) {
		this.monodroga = monodroga;
	}

	public String getLaboratorio() {
		return laboratorio;
	}

	public void setLaboratorio(String laboratorio) {
		this.laboratorio = laboratorio;
	}

	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	public Date getVencimiento() {
		return vencimiento;
	}

	public void setVencimiento(Date vencimiento) {
		this.vencimiento = vencimiento;
	}

	public List<Traza> getTrazaList() {
		return trazaList;
	}

	public void setTrazaList(List<Traza> trazaList) {
		this.trazaList = trazaList;
	}
}

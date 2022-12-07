package ar.com.scienza.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ar.com.scienza.base.ScienzaEntity;


@Entity
@Table(name="pedido_material")
public class PedidoMaterial extends ScienzaEntity { 

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "pedido_id", referencedColumnName = "id", nullable = false)
	private Pedido pedido;
	
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

	
	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
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
}

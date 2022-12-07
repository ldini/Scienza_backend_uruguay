package ar.com.scienza.entity;

import java.math.BigDecimal;
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
import ar.com.scienza.utils.BigDecimalUtil;


@Entity
@Table(name="pedido")
public class Pedido extends ScienzaEntity {
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "afiliado_id", referencedColumnName = "id", nullable = false)
	private Afiliado afiliado;
	
	@Column(name="numero_pedido", nullable = false)
	private Integer numeroPedido;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_pedido", nullable = false)
    private Date fechaPedido;

	@Column(name="canal", nullable = true, length = 100)
	private String canal;

	@Column(name="sector", nullable = true, length = 100)
	private String sector;

	@Column(name="clasificacion", nullable = false, length = 50)
	private String clasificacion;

	@Column(name="estado", nullable = false, length = 50)
	private String estado;

	@Column(name="domicilio", nullable = true, length = 100)
	private String domicilio;

	@Column(name="farmacia", nullable = true, length = 100)
	private String farmacia;

	@Column(name = "importe_cob_parcial", precision = 15, scale = 2, nullable = true)
	private BigDecimal importeCoberturaParcial;

	@Column(name="medio_de_pago", nullable = true, length = 50)
	private String medioDePago;


	@OneToMany(
		fetch = FetchType.LAZY, 
		mappedBy = "pedido", 
		cascade = {
			CascadeType.DETACH,
	        CascadeType.MERGE,
	        CascadeType.REFRESH,
	        CascadeType.PERSIST	
		}
	)
	private List<PedidoMaterial> materialList = new ArrayList<PedidoMaterial>();

	@OneToMany(
		fetch = FetchType.LAZY, 
		mappedBy = "pedido", 
		cascade = {
			CascadeType.DETACH,
	        CascadeType.MERGE,
	        CascadeType.REFRESH,
	        CascadeType.PERSIST	
		}
	)
	private List<Entrega> entregaList = new ArrayList<Entrega>();

	
	public Afiliado getAfiliado() {
		return afiliado;
	}

	public void setAfiliado(Afiliado afiliado) {
		this.afiliado = afiliado;
	}

	public Integer getNumeroPedido() {
		return numeroPedido;
	}

	public void setNumeroPedido(Integer numeroPedido) {
		this.numeroPedido = numeroPedido;
	}

	public Date getFechaPedido() {
		return fechaPedido;
	}

	public void setFechaPedido(Date fechaPedido) {
		this.fechaPedido = fechaPedido;
	}

	public String getCanal() {
		return canal;
	}

	public void setCanal(String canal) {
		this.canal = canal;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getClasificacion() {
		return clasificacion;
	}

	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getFarmacia() {
		return farmacia;
	}

	public void setFarmacia(String farmacia) {
		this.farmacia = farmacia;
	}

	public BigDecimal getImporteCoberturaParcial() {
		return BigDecimalUtil.normalize(importeCoberturaParcial);
	}

	public void setImporteCoberturaParcial(BigDecimal importeCoberturaParcial) {
		this.importeCoberturaParcial = importeCoberturaParcial;
	}

	public String getMedioDePago() {
		return medioDePago;
	}

	public void setMedioDePago(String medioDePago) {
		this.medioDePago = medioDePago;
	}

	public List<PedidoMaterial> getMaterialList() {
		return materialList;
	}

	public void setMaterialList(List<PedidoMaterial> materialList) {
		this.materialList = materialList;
	}

	public List<Entrega> getEntregaList() {
		return entregaList;
	}

	public void setEntregaList(List<Entrega> entregaList) {
		this.entregaList = entregaList;
	}
}

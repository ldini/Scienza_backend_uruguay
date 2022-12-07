package ar.com.scienza.entity;

import java.math.BigDecimal;
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

import com.fasterxml.jackson.annotation.JsonIgnore;

import ar.com.scienza.base.ScienzaEntity;


@Entity
@Table(name="entrega")
public class Entrega extends ScienzaEntity {

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "pedido_id", referencedColumnName = "id", nullable = false)
	private Pedido pedido;
	
	@Column(name="numero_entrega", nullable = false)
	private Integer numeroEntrega;

	@Column(name="numero_remito", nullable = false, length = 20)
	private String numeroRemito;

	@Column(name="texto_fecha_entrega", nullable = false, length = 100)
	private String textoFechaEntrega;

	@Column(name="texto_turno", nullable = false, length = 100)
	private String textoTurno;

	@Column(name = "importe_abonado", precision = 15, scale = 2, nullable = true)
	private BigDecimal importeAbonado;

	@Column(name="medio_de_pago", nullable = true, length = 50)
	private String medioDePago;

	@Column(name="operador_logistico", nullable = true, length = 100)
	private String operadorLogistico;

	@Column(name="estado", nullable = false, length = 50)
	private String estado;

	@Column(name="letra_destinatario", nullable = true, length = 10)
	private String letraDestinatario;

	@OneToMany(
		fetch = FetchType.LAZY, 
		mappedBy = "entrega", 
		cascade = {
			CascadeType.DETACH,
	        CascadeType.MERGE,
	        CascadeType.REFRESH,
	        CascadeType.PERSIST	
		}
	)
	private List<EntregaMaterial> entregaMaterialList = new ArrayList<EntregaMaterial>();

	
	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Integer getNumeroEntrega() {
		return numeroEntrega;
	}

	public void setNumeroEntrega(Integer numeroEntrega) {
		this.numeroEntrega = numeroEntrega;
	}

	public String getNumeroRemito() {
		return numeroRemito;
	}

	public void setNumeroRemito(String numeroRemito) {
		this.numeroRemito = numeroRemito;
	}

	public String getTextoFechaEntrega() {
		return textoFechaEntrega;
	}

	public void setTextoFechaEntrega(String textoFechaEntrega) {
		this.textoFechaEntrega = textoFechaEntrega;
	}

	public String getTextoTurno() {
		return textoTurno;
	}

	public void setTextoTurno(String textoTurno) {
		this.textoTurno = textoTurno;
	}

	public BigDecimal getImporteAbonado() {
		return importeAbonado;
	}

	public void setImporteAbonado(BigDecimal importeAbonado) {
		this.importeAbonado = importeAbonado;
	}

	public String getMedioDePago() {
		return medioDePago;
	}

	public void setMedioDePago(String medioDePago) {
		this.medioDePago = medioDePago;
	}

	public String getOperadorLogistico() {
		return operadorLogistico;
	}

	public void setOperadorLogistico(String operadorLogistico) {
		this.operadorLogistico = operadorLogistico;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getLetraDestinatario() {
		return letraDestinatario;
	}

	public void setLetraDestinatario(String letraDestinatario) {
		this.letraDestinatario = letraDestinatario;
	}

	public List<EntregaMaterial> getEntregaMaterialList() {
		return entregaMaterialList;
	}

	public void setEntregaMaterialList(List<EntregaMaterial> entregaMaterialList) {
		this.entregaMaterialList = entregaMaterialList;
	}
}

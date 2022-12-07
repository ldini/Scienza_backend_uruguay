package ar.com.scienza.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderResultResponseDto extends BodyDto {

	@JsonProperty("idOrder")
	private Integer pedidoId;
	
	@JsonProperty("type")
	private String tipo;

	@JsonProperty("status")
	private String estado;

	@JsonProperty("statusCode")
	private String codigoEstado;

	@JsonProperty("orderNumber")
	private Integer numeroPedido;
	
	@JsonProperty("deliveryDate")
	private String fechaEntrega;

	@JsonProperty("partial")
	private Boolean parcial;

	@JsonProperty("coordinable")
	private Boolean coordinable;

	@JsonProperty("allowUploads")
	private Boolean allowUploads;

	
	public Integer getPedidoId() {
		return pedidoId;
	}

	public void setPedidoId(Integer pedidoId) {
		this.pedidoId = pedidoId;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCodigoEstado() {
		return codigoEstado;
	}

	public void setCodigoEstado(String codigoEstado) {
		this.codigoEstado = codigoEstado;
	}

	public Integer getNumeroPedido() {
		return numeroPedido;
	}

	public void setNumeroPedido(Integer numeroPedido) {
		this.numeroPedido = numeroPedido;
	}

	public String getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(String fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public Boolean getParcial() {
		return parcial;
	}

	public void setParcial(Boolean parcial) {
		this.parcial = parcial;
	}

	public Boolean getCoordinable() {
		return coordinable;
	}

	public void setCoordinable(Boolean coordinable) {
		this.coordinable = coordinable;
	}

	public Boolean getAllowUploads() {
		return allowUploads;
	}

	public void setAllowUploads(Boolean allowUploads) {
		this.allowUploads = allowUploads;
	}
}

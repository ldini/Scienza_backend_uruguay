package ar.com.scienza.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AdminQueryOrderResponseDto extends BodyDto {

	@JsonProperty("orderNumber")
	private Integer numeroPedido;
	
	@JsonProperty("orderStatus")
	private String estadoPedido;
	
	@JsonProperty("deliveryNumber")
	private Integer numeroEntrega;
	
	@JsonProperty("deliveryStatus")
	private String estadoEntrega;
	
	@JsonProperty("deliveryDate")
	private String fechaEntrega;
	
	@JsonProperty("deliveryAddress")
	private String domicilioEntrega;

	
	public Integer getNumeroPedido() {
		return numeroPedido;
	}

	public void setNumeroPedido(Integer numeroPedido) {
		this.numeroPedido = numeroPedido;
	}

	public String getEstadoPedido() {
		return estadoPedido;
	}

	public void setEstadoPedido(String estadoPedido) {
		this.estadoPedido = estadoPedido;
	}

	public Integer getNumeroEntrega() {
		return numeroEntrega;
	}

	public void setNumeroEntrega(Integer numeroEntrega) {
		this.numeroEntrega = numeroEntrega;
	}

	public String getEstadoEntrega() {
		return estadoEntrega;
	}

	public void setEstadoEntrega(String estadoEntrega) {
		this.estadoEntrega = estadoEntrega;
	}

	public String getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(String fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public String getDomicilioEntrega() {
		return domicilioEntrega;
	}

	public void setDomicilioEntrega(String domicilioEntrega) {
		this.domicilioEntrega = domicilioEntrega;
	}
}

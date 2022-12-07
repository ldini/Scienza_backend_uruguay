package ar.com.scienza.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DeliveryCordinationRequestDto {
	
	@NotNull(message="acceptCoordination {required}")
	@JsonProperty("acceptCoordination")
	private Boolean aceptaCoordinacion;
	
	@NotNull(message="orderNumber {required}")
	@JsonProperty("orderNumber")
	private Integer numeroPedido;
	
	@NotNull(message="deliveryPosition {required}")
	@JsonProperty("deliveryPosition")
	private Integer posicionEntrega;

	@JsonProperty("chosenDate")
	private String fechaElegida;

	@JsonProperty("chosenAddress")
	private String domicilioElegido;

	@JsonProperty("paymentMethod")
	private String medioDePago;

	
	public Boolean getAceptaCoordinacion() {
		return aceptaCoordinacion;
	}

	public void setAceptaCoordinacion(Boolean aceptaCoordinacion) {
		this.aceptaCoordinacion = aceptaCoordinacion;
	}

	public Integer getNumeroPedido() {
		return numeroPedido;
	}

	public void setNumeroPedido(Integer numeroPedido) {
		this.numeroPedido = numeroPedido;
	}

	public Integer getPosicionEntrega() {
		return posicionEntrega;
	}

	public void setPosicionEntrega(Integer posicionEntrega) {
		this.posicionEntrega = posicionEntrega;
	}

	public String getFechaElegida() {
		return fechaElegida;
	}

	public void setFechaElegida(String fechaElegida) {
		this.fechaElegida = fechaElegida;
	}

	public String getDomicilioElegido() {
		return domicilioElegido;
	}

	public void setDomicilioElegido(String domicilioElegido) {
		this.domicilioElegido = domicilioElegido;
	}

	public String getMedioDePago() {
		return medioDePago;
	}

	public void setMedioDePago(String medioDePago) {
		this.medioDePago = medioDePago;
	}	
}

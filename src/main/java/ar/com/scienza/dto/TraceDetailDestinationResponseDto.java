package ar.com.scienza.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TraceDetailDestinationResponseDto extends BodyDto {

	@JsonProperty("destination")
	private String destino;
	
	@JsonProperty("orderNumber")
	private Integer numeroPedido;
	
	@JsonProperty("receipt")
	private String remito;
	
	@JsonProperty("date")
	private String fecha;
	
	@JsonProperty("client")
	private String cliente;

	
	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public Integer getNumeroPedido() {
		return numeroPedido;
	}

	public void setNumeroPedido(Integer numeroPedido) {
		this.numeroPedido = numeroPedido;
	}

	public String getRemito() {
		return remito;
	}

	public void setRemito(String remito) {
		this.remito = remito;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
}

package ar.com.scienza.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DeliveryLocationResponseDto extends BodyDto {

	@JsonProperty("distributor")
	private String distribuidor;
	
	@JsonProperty("patent")
	private String patente;

	@JsonProperty("entregaEvento")
	private String entregaEvento;

	@JsonProperty("entregaEventoFecha")
	private String entregaEventoFecha;

	@JsonProperty("entregaMarca")
	private String entregaMarca;


	public String getDistribuidor() {
		return distribuidor;
	}

	public void setDistribuidor(String distribuidor) {
		this.distribuidor = distribuidor;
	}

	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

	public void setEntregaEvento(String entregaEvento) {
		this.entregaEvento = entregaEvento;
	}

	public void setEntregaEventoFecha(String entregaEventoFecha) {
		this.entregaEventoFecha = entregaEventoFecha;
	}

	public void setEntregaMarca(String entregaMarca) {
		this.entregaMarca = entregaMarca;
	}

	public String getEntregaEvento() {
		return entregaEvento;
	}

	public String getEntregaEventoFecha() {
		return entregaEventoFecha;
	}

	public String getEntregaMarca() {
		return entregaMarca;
	}
}
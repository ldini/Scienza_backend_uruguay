package ar.com.scienza.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TraceDetailResponseDto extends BodyDto {

	@JsonProperty("product")
	private String producto;
	
	@JsonProperty("laboratory")
	private String laboratorio;
	
	@JsonProperty("batch")
	private String lote;
	
	@JsonProperty("expirationDate")
	private String vencimiento;
	
	@JsonProperty("country")
	private String paisOrigen;
	
	@JsonProperty("origin")
	private TraceDetailOriginResponseDto origen;

	@JsonProperty("destination")
	private TraceDetailDestinationResponseDto destino;

	
	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
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

	public String getVencimiento() {
		return vencimiento;
	}

	public void setVencimiento(String vencimiento) {
		this.vencimiento = vencimiento;
	}

	public String getPaisOrigen() {
		return paisOrigen;
	}

	public void setPaisOrigen(String paisOrigen) {
		this.paisOrigen = paisOrigen;
	}

	public TraceDetailOriginResponseDto getOrigen() {
		return origen;
	}

	public void setOrigen(TraceDetailOriginResponseDto origen) {
		this.origen = origen;
	}

	public TraceDetailDestinationResponseDto getDestino() {
		return destino;
	}

	public void setDestino(TraceDetailDestinationResponseDto destino) {
		this.destino = destino;
	}
}

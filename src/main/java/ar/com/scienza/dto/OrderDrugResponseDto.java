package ar.com.scienza.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderDrugResponseDto {

	@JsonIgnore
	private Integer sapId;
	
	@JsonProperty("name")
	private String nombre;
	
	@JsonProperty("amount")
	private Integer cantidad;
	
	@JsonProperty("laboratory")
	private String laboratorio;

	
	public Integer getSapId() {
		return sapId;
	}

	public void setSapId(Integer sapId) {
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

	public String getLaboratorio() {
		return laboratorio;
	}

	public void setLaboratorio(String laboratorio) {
		this.laboratorio = laboratorio;
	}
}

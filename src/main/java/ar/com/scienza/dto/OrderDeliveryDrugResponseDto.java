package ar.com.scienza.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderDeliveryDrugResponseDto {

	@JsonIgnore
	private Integer sapId;
	
	@JsonProperty("name")
	private String nombre;
	
	@JsonProperty("amount")
	private Integer cantidad;

	@JsonProperty("monodrug")
	private String monodroga;
	
	@JsonProperty("laboratory")
	private String laboratorio;
	
	@JsonProperty("batch")
	private String lote;
	
	@JsonProperty("expirationDate")
	private String vencimiento;
	
	@JsonProperty("traces")
	private List<String> trazas = new ArrayList<String>();

	
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

	public List<String> getTrazas() {
		return trazas;
	}

	public void setTrazas(List<String> trazas) {
		this.trazas = trazas;
	}
}

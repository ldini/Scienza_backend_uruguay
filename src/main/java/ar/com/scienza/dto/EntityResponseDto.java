package ar.com.scienza.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EntityResponseDto extends BodyDto {

	@JsonProperty("code")
	private String codigo;
	
	@JsonProperty("description")
	private String descripcion;

	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}

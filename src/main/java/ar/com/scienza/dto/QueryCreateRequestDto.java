package ar.com.scienza.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QueryCreateRequestDto {
	
	@NotNull(message="idCategoryType {required}")
	@JsonProperty("idCategoryType")
	private Integer tipoCategoriaId;
	
	@NotEmpty(message="description {required}")
	@JsonProperty("description")
	private String descripcion;


	public Integer getTipoCategoriaId() {
		return tipoCategoriaId;
	}

	public void setTipoCategoriaId(Integer tipoCategoriaId) {
		this.tipoCategoriaId = tipoCategoriaId;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}

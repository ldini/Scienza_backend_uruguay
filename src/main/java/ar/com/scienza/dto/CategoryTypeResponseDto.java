package ar.com.scienza.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CategoryTypeResponseDto extends BodyDto {

	@JsonProperty("id")
	private Integer id;
	
	@JsonProperty("name")
	private String nombre;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}

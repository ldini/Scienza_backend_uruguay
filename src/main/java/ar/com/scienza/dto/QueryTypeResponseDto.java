package ar.com.scienza.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QueryTypeResponseDto extends BodyDto {

	@JsonProperty("id")
	private Integer id;
	
	@JsonProperty("name")
	private String nombre;
	
	@JsonProperty("categories")
	private List<CategoryTypeResponseDto> tipoCategoriaList = new ArrayList<CategoryTypeResponseDto>();

	
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

	public List<CategoryTypeResponseDto> getTipoCategoriaList() {
		return tipoCategoriaList;
	}

	public void setTipoCategoriaList(List<CategoryTypeResponseDto> tipoCategoriaList) {
		this.tipoCategoriaList = tipoCategoriaList;
	}
}

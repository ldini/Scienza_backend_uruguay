package ar.com.scienza.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TemplateResultResponseDto extends BodyDto {

	@JsonProperty("idTemplate")
	private Integer templateId;
	
	@JsonProperty("title")
	private String titulo;
	
	@JsonProperty("description")
	private String descripcion;

	
	public Integer getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}

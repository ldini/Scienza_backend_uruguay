package ar.com.scienza.dto;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TemplateRequestDto {

	@JsonProperty("idTemplate")
	private Integer templateId;

	@NotEmpty(message="title {required}")
	@JsonProperty("title")
	private String titulo;
	
	@NotEmpty(message="description {required}")
	@JsonProperty("description")
	private String descripcion;
	
	@NotEmpty(message="content {required}")
	@JsonProperty("content")
	private String contenido;

	
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

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
}

package ar.com.scienza.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DocumentationFileResponseDto extends BodyDto {

	@JsonProperty("idDocumentation")
	private Integer documentacionId;
	
	@JsonProperty("fileName")
	private String nombreArchivo;
	
	@JsonProperty("fileURL")
	private String urlArchivo;
	
	@JsonProperty("dateCreation")
	private String fechaCreacion;

	
	public Integer getDocumentacionId() {
		return documentacionId;
	}

	public void setDocumentacionId(Integer documentacionId) {
		this.documentacionId = documentacionId;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public String getUrlArchivo() {
		return urlArchivo;
	}

	public void setUrlArchivo(String urlArchivo) {
		this.urlArchivo = urlArchivo;
	}

	public String getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
}

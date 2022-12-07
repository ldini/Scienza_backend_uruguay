package ar.com.scienza.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DocumentationImageResultResponseDto extends BodyDto {

	@JsonProperty("idDocumentation")
	private Integer documentacionId;
	
	@JsonProperty("smallImageURL")
	private String urlImagenChica;
	
	@JsonProperty("imageURL")
	private String urlImagen;
	
	@JsonProperty("dateCreation")
	private String fechaCreacion;

	
	public Integer getDocumentacionId() {
		return documentacionId;
	}

	public void setDocumentacionId(Integer documentacionId) {
		this.documentacionId = documentacionId;
	}

	public String getUrlImagenChica() {
		return urlImagenChica;
	}

	public void setUrlImagenChica(String urlImagenChica) {
		this.urlImagenChica = urlImagenChica;
	}

	public String getUrlImagen() {
		return urlImagen;
	}

	public void setUrlImagen(String urlImagen) {
		this.urlImagen = urlImagen;
	}

	public String getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
}

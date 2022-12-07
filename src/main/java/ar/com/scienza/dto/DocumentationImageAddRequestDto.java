package ar.com.scienza.dto;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DocumentationImageAddRequestDto {
	
	@NotEmpty(message="fileName {required}")
	@JsonProperty("fileName")
	private String nombreArchivo;
	
	@NotEmpty(message="image {required}")
	@JsonProperty("image")
	private String imagen;

	
	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
}

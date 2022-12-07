package ar.com.scienza.dto;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DocumentationFileAddRequestDto {
	
	@NotEmpty(message="fileName {required}")
	@JsonProperty("fileName")
	private String nombreArchivo;
	
	@NotEmpty(message="file {required}")
	@JsonProperty("file")
	private String archivo;

	
	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public String getArchivo() {
		return archivo;
	}

	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}
}

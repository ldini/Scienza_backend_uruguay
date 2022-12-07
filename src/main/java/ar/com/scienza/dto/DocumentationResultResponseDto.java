package ar.com.scienza.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DocumentationResultResponseDto extends BodyDto {

	@JsonProperty("files")
	private List<DocumentationFileResultResponseDto> archivoList = new ArrayList<DocumentationFileResultResponseDto>();


	public List<DocumentationFileResultResponseDto> getArchivoList() {
		return archivoList;
	}

	public void setArchivoList(List<DocumentationFileResultResponseDto> archivoList) {
		this.archivoList = archivoList;
	}
}

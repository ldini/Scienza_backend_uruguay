package ar.com.scienza.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AdminQueryResultResponseDto extends BodyDto {

	@JsonProperty("myQueries")
	private List<AdminQueryResultDetailResponseDto> misConsultasList = new ArrayList<AdminQueryResultDetailResponseDto>();

	@JsonProperty("pendings")
	private List<AdminQueryResultDetailResponseDto> pendientesList = new ArrayList<AdminQueryResultDetailResponseDto>();

	
	public List<AdminQueryResultDetailResponseDto> getMisConsultasList() {
		return misConsultasList;
	}

	public void setMisConsultasList(List<AdminQueryResultDetailResponseDto> misConsultasList) {
		this.misConsultasList = misConsultasList;
	}

	public List<AdminQueryResultDetailResponseDto> getPendientesList() {
		return pendientesList;
	}

	public void setPendientesList(List<AdminQueryResultDetailResponseDto> pendientesList) {
		this.pendientesList = pendientesList;
	}
}

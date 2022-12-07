package ar.com.scienza.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FrequentQuestionResponseDto extends BodyDto {

	@JsonProperty("category")
	private String categoria;

	@JsonProperty("questions")
	private List<FrequentQuestionDetailResponseDto> preguntaList = new ArrayList<FrequentQuestionDetailResponseDto>();

	
	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public List<FrequentQuestionDetailResponseDto> getPreguntaList() {
		return preguntaList;
	}

	public void setPreguntaList(List<FrequentQuestionDetailResponseDto> preguntaList) {
		this.preguntaList = preguntaList;
	}
}

package ar.com.scienza.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FrequentQuestionDetailResponseDto extends BodyDto {

	@JsonProperty("question")
	private String pregunta;

	@JsonProperty("answer")
	private String respuesta;

	
	public String getPregunta() {
		return pregunta;
	}

	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}

	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
}

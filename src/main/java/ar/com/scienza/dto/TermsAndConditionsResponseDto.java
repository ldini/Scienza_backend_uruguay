package ar.com.scienza.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TermsAndConditionsResponseDto extends BodyDto {

	@JsonProperty("html")
	private String html;

	
	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}
}

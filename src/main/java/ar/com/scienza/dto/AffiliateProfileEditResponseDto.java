package ar.com.scienza.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AffiliateProfileEditResponseDto extends BodyDto {

	@JsonProperty("pendingVerifyAdmin")
	private Boolean pendienteVerificacion = false;
	
	@JsonProperty("message")
	private String message;

	
	public Boolean getPendienteVerificacion() {
		return pendienteVerificacion;
	}

	public void setPendienteVerificacion(Boolean pendienteVerificacion) {
		this.pendienteVerificacion = pendienteVerificacion;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}

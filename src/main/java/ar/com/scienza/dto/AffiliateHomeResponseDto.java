package ar.com.scienza.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AffiliateHomeResponseDto extends BodyDto {

	@JsonProperty("pendingCoordination")
	private Boolean coordinacionPendiente;
	
	@JsonProperty("unreadNotifications")
	private Integer notificacionesNoLeidas;

	@JsonProperty("unreadQueries")
	private Integer consultasNoLeidas;

	@JsonProperty("allowUploadDocumentation")
	private Boolean permiteEnvioDocumentacion;

	@JsonProperty("prescriptionsUploaded")
	private Integer recetasSubidas;

	@JsonProperty("otherStudiesUploaded")
	private Integer otrosEstudiosSubidos;

	
	public Boolean getCoordinacionPendiente() {
		return coordinacionPendiente;
	}

	public void setCoordinacionPendiente(Boolean coordinacionPendiente) {
		this.coordinacionPendiente = coordinacionPendiente;
	}

	public Integer getNotificacionesNoLeidas() {
		return notificacionesNoLeidas;
	}

	public void setNotificacionesNoLeidas(Integer notificacionesNoLeidas) {
		this.notificacionesNoLeidas = notificacionesNoLeidas;
	}

	public Integer getConsultasNoLeidas() {
		return consultasNoLeidas;
	}

	public void setConsultasNoLeidas(Integer consultasNoLeidas) {
		this.consultasNoLeidas = consultasNoLeidas;
	}

	public Boolean getPermiteEnvioDocumentacion() {
		return permiteEnvioDocumentacion;
	}

	public void setPermiteEnvioDocumentacion(Boolean permiteEnvioDocumentacion) {
		this.permiteEnvioDocumentacion = permiteEnvioDocumentacion;
	}

	public Integer getRecetasSubidas() {
		return recetasSubidas;
	}

	public void setRecetasSubidas(Integer recetasSubidas) {
		this.recetasSubidas = recetasSubidas;
	}

	public Integer getOtrosEstudiosSubidos() {
		return otrosEstudiosSubidos;
	}

	public void setOtrosEstudiosSubidos(Integer otrosEstudiosSubidos) {
		this.otrosEstudiosSubidos = otrosEstudiosSubidos;
	}
}

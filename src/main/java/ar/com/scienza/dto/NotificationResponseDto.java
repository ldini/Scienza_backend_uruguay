package ar.com.scienza.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NotificationResponseDto extends BodyDto {

	@JsonProperty("idNotification")
	private Integer notificationId;
	
	@JsonProperty("title")
	private String titulo;
	
	@JsonProperty("message")
	private String mensaje;

	@JsonProperty("dateNotification")
	private String fechaNotificacion;

	
	public Integer getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(Integer notificationId) {
		this.notificationId = notificationId;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getFechaNotificacion() {
		return fechaNotificacion;
	}

	public void setFechaNotificacion(String fechaNotificacion) {
		this.fechaNotificacion = fechaNotificacion;
	}
}

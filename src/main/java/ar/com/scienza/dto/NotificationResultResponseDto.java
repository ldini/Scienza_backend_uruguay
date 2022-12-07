package ar.com.scienza.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NotificationResultResponseDto extends BodyDto {

	@JsonProperty("idNotification")
	private Integer notificationId;
	
	@JsonProperty("title")
	private String titulo;
	
	@JsonProperty("message")
	private String mensaje;

	@JsonProperty("dateNotification")
	private String fechaNotificacion;

	@JsonProperty("new")
	private Boolean nuevo;

	
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

	public Boolean getNuevo() {
		return nuevo;
	}

	public void setNuevo(Boolean nuevo) {
		this.nuevo = nuevo;
	}
}

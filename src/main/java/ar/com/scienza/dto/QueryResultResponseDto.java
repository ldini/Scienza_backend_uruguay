package ar.com.scienza.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QueryResultResponseDto extends BodyDto {

	@JsonProperty("idQuery")
	private Integer consultaId;
	
	@JsonProperty("ticket")
	private String ticket;
	
	@JsonProperty("queryType")
	private String tipoConsulta;
	
	@JsonProperty("categoryType")
	private String tipoCategoria;

	@JsonProperty("unreadMessages")
	private Integer mensajesNoLeidos;
	
	@JsonProperty("lastMessageDate")
	private String fechaUltimoMensaje;

	@JsonProperty("lastMessage")
	private String ultimoMensaje;

	@JsonProperty("closed")
	private Boolean finalizado;

	@Deprecated
	@JsonProperty("queryDate")
	private String fechaConsulta;

	@Deprecated
	@JsonProperty("answer")
	private String respuesta;


	public Integer getConsultaId() {
		return consultaId;
	}

	public void setConsultaId(Integer consultaId) {
		this.consultaId = consultaId;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public String getTipoConsulta() {
		return tipoConsulta;
	}

	public void setTipoConsulta(String tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
	}

	public String getTipoCategoria() {
		return tipoCategoria;
	}

	public void setTipoCategoria(String tipoCategoria) {
		this.tipoCategoria = tipoCategoria;
	}

	public Integer getMensajesNoLeidos() {
		return mensajesNoLeidos;
	}

	public void setMensajesNoLeidos(Integer mensajesNoLeidos) {
		this.mensajesNoLeidos = mensajesNoLeidos;
	}

	public String getFechaUltimoMensaje() {
		return fechaUltimoMensaje;
	}

	public void setFechaUltimoMensaje(String fechaUltimoMensaje) {
		this.fechaUltimoMensaje = fechaUltimoMensaje;
	}

	public String getUltimoMensaje() {
		return ultimoMensaje;
	}

	public void setUltimoMensaje(String ultimoMensaje) {
		this.ultimoMensaje = ultimoMensaje;
	}

	public Boolean getFinalizado() {
		return finalizado;
	}

	public void setFinalizado(Boolean finalizado) {
		this.finalizado = finalizado;
	}

	public String getFechaConsulta() {
		return fechaConsulta;
	}

	public void setFechaConsulta(String fechaConsulta) {
		this.fechaConsulta = fechaConsulta;
	}

	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
}

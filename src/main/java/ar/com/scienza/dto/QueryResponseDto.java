package ar.com.scienza.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QueryResponseDto extends BodyDto {

	@JsonProperty("idQuery")
	private Integer consultaId;
	
	@JsonProperty("publishKey")
	private String publishKey;

	@JsonProperty("subscribeKey")
	private String subscribeKey;

	@JsonProperty("channel")
	private String canal;

	@JsonProperty("ticket")
	private String ticket;

	@JsonProperty("toolbarDescription")
	private String toolbarDescription;

	@JsonProperty("footerDescription")
	private String footerDescription;

	@JsonProperty("finished")
	private Boolean finalizado;

	@JsonProperty("messages")
	private List<QueryMessageResponseDto> messageList = new ArrayList<QueryMessageResponseDto>();
	
	@Deprecated
	@JsonProperty("queryType")
	private String tipoConsulta;

	@Deprecated
	@JsonProperty("categoryType")
	private String tipoCategoria;

	@Deprecated
	@JsonProperty("queryDate")
	private String fechaConsulta;

	@Deprecated
	@JsonProperty("description")
	private String descripcion;

	@Deprecated
	@JsonProperty("answered")
	private Boolean respondido;

	@Deprecated
	@JsonProperty("answerResponsible")
	private String responsableRespuesta;

	@Deprecated
	@JsonProperty("answerDate")
	private String fechaRespuesta;

	
	public Integer getConsultaId() {
		return consultaId;
	}

	public void setConsultaId(Integer consultaId) {
		this.consultaId = consultaId;
	}

	public String getPublishKey() {
		return publishKey;
	}

	public void setPublishKey(String publishKey) {
		this.publishKey = publishKey;
	}

	public String getSubscribeKey() {
		return subscribeKey;
	}

	public void setSubscribeKey(String subscribeKey) {
		this.subscribeKey = subscribeKey;
	}

	public String getCanal() {
		return canal;
	}

	public void setCanal(String canal) {
		this.canal = canal;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public String getToolbarDescription() {
		return toolbarDescription;
	}

	public void setToolbarDescription(String toolbarDescription) {
		this.toolbarDescription = toolbarDescription;
	}

	public String getFooterDescription() {
		return footerDescription;
	}

	public void setFooterDescription(String footerDescription) {
		this.footerDescription = footerDescription;
	}

	public Boolean getFinalizado() {
		return finalizado;
	}

	public void setFinalizado(Boolean finalizado) {
		this.finalizado = finalizado;
	}

	public List<QueryMessageResponseDto> getMessageList() {
		return messageList;
	}

	public void setMessageList(List<QueryMessageResponseDto> messageList) {
		this.messageList = messageList;
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

	public String getFechaConsulta() {
		return fechaConsulta;
	}

	public void setFechaConsulta(String fechaConsulta) {
		this.fechaConsulta = fechaConsulta;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Boolean getRespondido() {
		return respondido;
	}

	public void setRespondido(Boolean respondido) {
		this.respondido = respondido;
	}

	public String getResponsableRespuesta() {
		return responsableRespuesta;
	}

	public void setResponsableRespuesta(String responsableRespuesta) {
		this.responsableRespuesta = responsableRespuesta;
	}

	public String getFechaRespuesta() {
		return fechaRespuesta;
	}

	public void setFechaRespuesta(String fechaRespuesta) {
		this.fechaRespuesta = fechaRespuesta;
	}
}

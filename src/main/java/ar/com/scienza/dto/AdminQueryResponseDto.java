package ar.com.scienza.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AdminQueryResponseDto extends BodyDto {

	@JsonProperty("idQuery")
	private Integer consultaId;

	@JsonProperty("publishKey")
	private String publishKey;

	@JsonProperty("subscribeKey")
	private String subscribeKey;

	@JsonProperty("channel")
	private String canal;
	
	@JsonProperty("firstName")
	private String nombre;
	
	@JsonProperty("lastName")
	private String apellido;

	@JsonProperty("sapId")
	private Long sapId;
	
	@JsonProperty("queries")
	private List<AdminQueryHistoryResponseDto> consultaList = new ArrayList<AdminQueryHistoryResponseDto>();

	@JsonProperty("orders")
	private List<AdminQueryOrderResponseDto> pedidoList = new ArrayList<AdminQueryOrderResponseDto>();

	@JsonProperty("ticket")
	private String ticket;

	@JsonProperty("queryDate")
	private String fechaConsulta;
	
	@JsonProperty("queryType")
	private String tipoConsulta;
	
	@JsonProperty("categoryType")
	private String tipoCategoria;
	
	@JsonProperty("finished")
	private Boolean finalizado;

	@JsonProperty("messages")
	private List<QueryMessageResponseDto> messageList = new ArrayList<QueryMessageResponseDto>();

	
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Long getSapId() {
		return sapId;
	}

	public void setSapId(Long sapId) {
		this.sapId = sapId;
	}

	public List<AdminQueryHistoryResponseDto> getConsultaList() {
		return consultaList;
	}

	public void setConsultaList(List<AdminQueryHistoryResponseDto> consultaList) {
		this.consultaList = consultaList;
	}

	public List<AdminQueryOrderResponseDto> getPedidoList() {
		return pedidoList;
	}

	public void setPedidoList(List<AdminQueryOrderResponseDto> pedidoList) {
		this.pedidoList = pedidoList;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public String getFechaConsulta() {
		return fechaConsulta;
	}

	public void setFechaConsulta(String fechaConsulta) {
		this.fechaConsulta = fechaConsulta;
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
}

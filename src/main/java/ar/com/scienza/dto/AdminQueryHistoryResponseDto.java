package ar.com.scienza.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AdminQueryHistoryResponseDto extends BodyDto {

	@JsonProperty("idQuery")
	private Integer consultaId;
	
	@JsonProperty("ticket")
	private String ticket;
	
	@JsonProperty("queryType")
	private String tipoConsulta;

	@JsonProperty("queryDate")
	private String queryDate;
	
	@JsonProperty("categoryType")
	private String tipoCategoria;

	public String getQueryDate() {
		return queryDate;
	}

	public void setQueryDate(String queryDate) {
		this.queryDate = queryDate;
	}

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
}

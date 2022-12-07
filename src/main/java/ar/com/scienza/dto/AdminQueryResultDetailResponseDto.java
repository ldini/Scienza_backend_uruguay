package ar.com.scienza.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AdminQueryResultDetailResponseDto extends BodyDto {

	@JsonProperty("idQuery")
	private Integer consultaId;

	@JsonProperty("ticket")
	private String ticket;
	
	@JsonProperty("queryDate")
	private String fechaConsulta;
	
	@JsonProperty("firstName")
	private String nombre;
	
	@JsonProperty("lastName")
	private String apellido;
	
	@JsonProperty("sapId")
	private Long sapId;

	@JsonProperty("queryType")
	private String tipoConsulta;
	
	@JsonProperty("categoryType")
	private String tipoCategoria;

	
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

	public String getFechaConsulta() {
		return fechaConsulta;
	}

	public void setFechaConsulta(String fechaConsulta) {
		this.fechaConsulta = fechaConsulta;
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

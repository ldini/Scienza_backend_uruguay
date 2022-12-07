package ar.com.scienza.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AdminQueryHistoryRequestDto {
	
	@JsonProperty("ticket")
	private Integer ticket;
	
	@JsonProperty("sapId")
	private Long sapId;

	
	public Integer getTicket() {
		return ticket;
	}

	public void setTicket(Integer ticket) {
		this.ticket = ticket;
	}

	public Long getSapId() {
		return sapId;
	}

	public void setSapId(Long sapId) {
		this.sapId = sapId;
	}
}

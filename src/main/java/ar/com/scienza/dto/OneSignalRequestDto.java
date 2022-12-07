package ar.com.scienza.dto;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OneSignalRequestDto {

	@NotEmpty(message="playerId {required}")
	@JsonProperty("playerId")
	private String playerId;

	
	public String getPlayerId() {
		return playerId;
	}

	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}
}

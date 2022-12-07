package ar.com.scienza.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AffiliateBindingResponseDto extends BodyDto {

	@JsonProperty("user")
	private UserResponseDto usuario;
	
	@JsonProperty("relatedUsers")
	private List<UserResponseDto> otrosUsuarios = new ArrayList<UserResponseDto>();

	
	public UserResponseDto getUsuario() {
		return usuario;
	}

	public void setUsuario(UserResponseDto usuario) {
		this.usuario = usuario;
	}

	public List<UserResponseDto> getOtrosUsuarios() {
		return otrosUsuarios;
	}

	public void setOtrosUsuarios(List<UserResponseDto> otrosUsuarios) {
		this.otrosUsuarios = otrosUsuarios;
	}
}

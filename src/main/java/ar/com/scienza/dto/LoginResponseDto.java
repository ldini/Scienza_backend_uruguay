package ar.com.scienza.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginResponseDto extends BodyDto {

	@JsonProperty("token")
	private String token;

	@JsonProperty("role")
	private String rol;
	
	@JsonProperty("user")
	private UserResponseDto usuario;
	
	@JsonProperty("relatedUsers")
	private List<UserResponseDto> otrosUsuarios = new ArrayList<UserResponseDto>();

	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

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

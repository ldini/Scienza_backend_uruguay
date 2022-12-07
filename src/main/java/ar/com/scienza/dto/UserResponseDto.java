package ar.com.scienza.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserResponseDto extends BodyDto {

	@JsonProperty("avatar")
	private String avatar;
	
	@JsonProperty("firstName")
	private String nombre;
	
	@JsonProperty("lastName")
	private String apellido;

	@JsonProperty("identification")
	private String cedulaIdentidad;

	@JsonProperty("sapId")
	private Long sapId;

	@JsonProperty("verifyProfile")
	private Boolean verificarPerfil;
	
	@JsonProperty("grants")
	private List<String> permisos = new ArrayList<String>();

	
	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
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

	public String getcedulaIdentidad() {
		return cedulaIdentidad;
	}

	public void setcedulaIdentidad(String cedulaIdentidad) {
		this.cedulaIdentidad = cedulaIdentidad;
	}

	public Long getSapId() {
		return sapId;
	}

	public void setSapId(Long sapId) {
		this.sapId = sapId;
	}

	public Boolean getVerificarPerfil() {
		return verificarPerfil;
	}

	public void setVerificarPerfil(Boolean verificarPerfil) {
		this.verificarPerfil = verificarPerfil;
	}

	public List<String> getPermisos() {
		return permisos;
	}

	public void setPermisos(List<String> permisos) {
		this.permisos = permisos;
	}
}

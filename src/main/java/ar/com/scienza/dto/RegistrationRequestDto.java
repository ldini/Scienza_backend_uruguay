package ar.com.scienza.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegistrationRequestDto {

	@NotNull(message="identification {required}")
	@Max(message="identification {max_value}", value=9999999999L)
	@JsonProperty("identification")
	private String cedulaIdentidad;

	@NotNull(message="sapId {required}")
	@Max(message="El número de usuario {max_value}", value=999999999)
	@JsonProperty("sapId")
	private Long sapId;

	@NotEmpty(message="password {required}")
	@Length(message="La contraseña {min_lenght}", min=6)
	@JsonProperty("password")
	private String password;


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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}

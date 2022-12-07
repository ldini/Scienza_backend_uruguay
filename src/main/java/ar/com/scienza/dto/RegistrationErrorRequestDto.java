package ar.com.scienza.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegistrationErrorRequestDto {

	@NotEmpty(message="firstName {required}")
	@JsonProperty("firstName")
	private String nombre;
	
	@NotEmpty(message="lastName {required}")
	@JsonProperty("lastName")
	private String apellido;

	@NotNull(message="identification {required}")
	@Max(message="identification {max_value}", value=9999999999L)
	@JsonProperty("identification")
	private String cedulaIdentidad;

	@NotNull(message="sapId {required}")
	@Max(message="sapId {max_value}", value=999999999)
	@JsonProperty("sapId")
	private Long sapId;

	@NotEmpty(message="password {required}")
	@Length(message="password {min_lenght}", min=6)
	@JsonProperty("password")
	private String password;

	@NotEmpty(message="telephone {required}")
	@JsonProperty("telephone")
	private String telefono;

	@NotEmpty(message="email {required}")
	@JsonProperty("email")
	private String email;

	
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}

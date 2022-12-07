package ar.com.scienza.dto;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginRequestDto {

	@NotEmpty(message="userName {required}")
	@JsonProperty("userName")
	private String userName;

	@NotEmpty(message="password {required}")
	@JsonProperty("password")
	private String password;


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}

package ar.com.scienza.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ar.com.scienza.base.ScienzaEntity;

@Entity
@Table(name="usuario")
public class Usuario extends ScienzaEntity {
		
	@Column(name="username", nullable = false)
	private String userName;
	
	@Column(name="password", nullable = false)
	private String password;
	
	@Column(name="enabled", nullable = false, columnDefinition ="BIT", length = 1)
	private Boolean enabled = false;
	
	@Column(name="account_non_expired", nullable = false, columnDefinition ="BIT", length = 1)
	private Boolean accountNonExpired = true;
	
	@Column(name="credentials_non_expired", nullable = false, columnDefinition ="BIT", length = 1)
	private Boolean credentialsNonExpired = true;
	
	@Column(name="account_non_locked", nullable = false, columnDefinition ="BIT", length = 1)
	private Boolean accountNonLocked = true;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "rol_id", referencedColumnName = "id", nullable = false)
	private Rol rol;
	
	
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
	public Boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	public Boolean isAccountNonExpired() {
		return accountNonExpired;
	}
	public void setAccountNonExpired(Boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}
	public Boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}
	public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}
	public Boolean isAccountNonLocked() {
		return accountNonLocked;
	}
	public void setAccountNonLocked(Boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}
	public Rol getRol() {
		return rol;
	}
	public void setRol(Rol rol) {
		this.rol = rol;
	}
}

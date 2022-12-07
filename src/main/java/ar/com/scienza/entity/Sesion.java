package ar.com.scienza.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ar.com.scienza.base.ScienzaEntity;

@Entity
@Table(name="sesion")
public class Sesion extends ScienzaEntity {

	@Column(name="token", length = 50, nullable = false)
	private String token;

	@Column(name="player_id", length = 50, nullable = true)
	private String playerId;

	@Column(name = "tipo_dispositivo", length = 3, nullable = false)
	private String tipoDispositivo;

	@Column(name="rol", length = 50, nullable = false)
	private String rol;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "afiliado_id", referencedColumnName = "id")
	private Afiliado afiliado;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "administrador_id", referencedColumnName = "id")
	private Administrador administrador;

	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getPlayerId() {
		return playerId;
	}

	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}

	public String getTipoDispositivo() {
		return tipoDispositivo;
	}

	public void setTipoDispositivo(String tipoDispositivo) {
		this.tipoDispositivo = tipoDispositivo;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public Afiliado getAfiliado() {
		return afiliado;
	}

	public void setAfiliado(Afiliado afiliado) {
		this.afiliado = afiliado;
	}

	public Administrador getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}
}

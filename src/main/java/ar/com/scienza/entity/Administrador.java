package ar.com.scienza.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ar.com.scienza.base.ScienzaEntity;
import ar.com.scienza.enumerator.AvatarEnum;

@Entity
@Table(name="administrador")
public class Administrador extends ScienzaEntity {
	
	@Column(name="nombre", nullable = false, length = 50)
	private String nombre;
	
	@Column(name="apellido", nullable = false, length = 50)
	private String apellido;	

	@Column(name="telefono", nullable = false, length = 50)
	private Integer telefono;
	
	@Column(name="email", nullable = false, length = 50)
	private String email;
	
	@Column(name="operativo", columnDefinition = "BIT", length = 1, nullable = false)
	private Boolean operativo;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "usuario_id", referencedColumnName = "id", nullable = false)
	private Usuario usuario;

	
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

	public Integer getTelefono() {
		return telefono;
	}

	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getOperativo() {
		return operativo;
	}

	public void setOperativo(Boolean operativo) {
		this.operativo = operativo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	

	/**
	 * Devuelve el avatar del admin
	 * @return
	 */
	public String getAvatar() {
		return AvatarEnum.AVATAR_ADMIN.getCodigo();
	}
}

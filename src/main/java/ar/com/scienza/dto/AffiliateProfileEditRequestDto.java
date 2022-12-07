package ar.com.scienza.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AffiliateProfileEditRequestDto extends BodyDto {
	
	@NotEmpty(message="Nombre {required}")
	@JsonProperty("firstName")
	private String nombre;
	
	@NotEmpty(message="Apellido {required}")
	@JsonProperty("lastName")
	private String apellido;

	@JsonProperty("acronym")
	private String apodo;

	@NotEmpty(message="Sexo {required}")
	@JsonProperty("gender")
	private String sexo;

	@JsonProperty("birthdate")
	private String fechaNacimiento;

	@JsonProperty("province")
	private String provincia;

	@JsonProperty("locality")
	private String localidad;

	@JsonProperty("street")
	private String calle;

	@JsonProperty("streetNumber")
	private Integer calleNumero;

	@JsonProperty("floor")
	private String piso;

	@JsonProperty("department")
	private String departamento;

	@JsonProperty("postalCode")
	private String codigoPostal;

	@JsonProperty("personalPhone")
	private String telefonoParticular;

	@JsonProperty("workPhone")
	private String telefonoLaboral;

	@JsonProperty("cellPhone")
	private String telefonoCelular;

	@JsonProperty("cellCompanyCode")
	private String codigoCompaniaCelular;

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

	public String getApodo() {
		return apodo;
	}

	public void setApodo(String apodo) {
		this.apodo = apodo;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public Integer getCalleNumero() {
		return calleNumero;
	}

	public void setCalleNumero(Integer calleNumero) {
		this.calleNumero = calleNumero;
	}

	public String getPiso() {
		return piso;
	}

	public void setPiso(String piso) {
		this.piso = piso;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getTelefonoParticular() {
		return telefonoParticular;
	}

	public void setTelefonoParticular(String telefonoParticular) {
		this.telefonoParticular = telefonoParticular;
	}

	public String getTelefonoLaboral() {
		return telefonoLaboral;
	}

	public void setTelefonoLaboral(String telefonoLaboral) {
		this.telefonoLaboral = telefonoLaboral;
	}

	public String getTelefonoCelular() {
		return telefonoCelular;
	}

	public void setTelefonoCelular(String telefonoCelular) {
		this.telefonoCelular = telefonoCelular;
	}

	public String getCodigoCompaniaCelular() {
		return codigoCompaniaCelular;
	}

	public void setCodigoCompaniaCelular(String codigoCompaniaCelular) {
		this.codigoCompaniaCelular = codigoCompaniaCelular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	/**
	 * hashcode afiliado
	 */
	@Override
	public int hashCode() {
		int hashCode = 1;
		hashCode = 11 * hashCode + (nombre == null ? 0 : nombre.hashCode());
		hashCode = 11 * hashCode + (apellido == null ? 0 : apellido.hashCode());
		hashCode = 11 * hashCode + (apodo == null ? 0 : apodo.hashCode());
		hashCode = 11 * hashCode + (sexo == null ? 0 : sexo.hashCode());
		hashCode = 11 * hashCode + (fechaNacimiento == null ? 0 : fechaNacimiento.hashCode());
		hashCode = 11 * hashCode + (provincia == null ? 0 : provincia.hashCode());
		hashCode = 11 * hashCode + (localidad == null ? 0 : localidad.hashCode());
		hashCode = 11 * hashCode + (calle == null ? 0 : calle.hashCode());
		hashCode = 11 * hashCode + (calleNumero == null ? 0 : calleNumero.hashCode());
		hashCode = 11 * hashCode + (piso == null ? 0 : piso.hashCode());
		hashCode = 11 * hashCode + (departamento == null ? 0 : departamento.hashCode());
		hashCode = 11 * hashCode + (codigoPostal == null ? 0 : codigoPostal.hashCode());
		hashCode = 11 * hashCode + (telefonoParticular == null ? 0 : telefonoParticular.hashCode());
		hashCode = 11 * hashCode + (telefonoLaboral == null ? 0 : telefonoLaboral.hashCode());
		hashCode = 11 * hashCode + (telefonoCelular == null ? 0 : telefonoCelular.hashCode());
		hashCode = 11 * hashCode + (codigoCompaniaCelular == null ? 0 : codigoCompaniaCelular.hashCode());
		hashCode = 11 * hashCode + (email == null ? 0 : email.hashCode());				
		return hashCode;
	}
}

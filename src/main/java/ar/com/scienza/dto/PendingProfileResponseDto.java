package ar.com.scienza.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PendingProfileResponseDto extends BodyDto {

	@JsonProperty("idResult")
	private Integer bandejaId;
	
	@JsonProperty("sapId")
	private Long sapId;
	
	@JsonProperty("firstName")
	private String nombre;
	
	@JsonProperty("lastName")
	private String apellido;

	@JsonProperty("acronym")
	private String apodo;
	
	@JsonProperty("healthInsurance")
	private String seguroMedico;

	@JsonProperty("affiliateNumber")
	private String numeroAfiliado;
	
	@JsonProperty("identification")
	private String cedulaIdentidad;

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

	@JsonProperty("latitude")
	private String latitud;

	@JsonProperty("longitude")
	private String longitud;

	@JsonProperty("personalPhone")
	private String telefonoParticular;

	@JsonProperty("workPhone")
	private String telefonoLaboral;

	@JsonProperty("cellPhone")
	private String telefonoCelular;

	@JsonProperty("cellCompany")
	private String companiaCelular;

	@JsonProperty("email")
	private String email;
	
	@JsonProperty("modifiedPropertyList")
	private List<String> propiedadModificadaList = new ArrayList<String>();

	
	public Integer getBandejaId() {
		return bandejaId;
	}

	public void setBandejaId(Integer bandejaId) {
		this.bandejaId = bandejaId;
	}

	public Long getSapId() {
		return sapId;
	}

	public void setSapId(Long sapId) {
		this.sapId = sapId;
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

	public String getApodo() {
		return apodo;
	}

	public void setApodo(String apodo) {
		this.apodo = apodo;
	}

	public String getSeguroMedico() {
		return seguroMedico;
	}

	public void setSeguroMedico(String seguroMedico) {
		this.seguroMedico = seguroMedico;
	}

	public String getNumeroAfiliado() {
		return numeroAfiliado;
	}

	public void setNumeroAfiliado(String numeroAfiliado) {
		this.numeroAfiliado = numeroAfiliado;
	}

	public String getcedulaIdentidad() {
		return cedulaIdentidad;
	}

	public void setcedulaIdentidad(String cedulaIdentidad) {
		this.cedulaIdentidad = cedulaIdentidad;
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

	public String getLatitud() {
		return latitud;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	public String getLongitud() {
		return longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
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

	public String getCompaniaCelular() {
		return companiaCelular;
	}

	public void setCompaniaCelular(String companiaCelular) {
		this.companiaCelular = companiaCelular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getPropiedadModificadaList() {
		return propiedadModificadaList;
	}

	public void setPropiedadModificadaList(List<String> propiedadModificadaList) {
		this.propiedadModificadaList = propiedadModificadaList;
	}
}

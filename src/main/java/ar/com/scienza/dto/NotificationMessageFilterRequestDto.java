package ar.com.scienza.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NotificationMessageFilterRequestDto {

	@JsonProperty("namingList")
	private List<String> nombreApellidoList = new ArrayList<String>();

	@JsonProperty("healthInsuranceList")
	private List<String> seguroMedicoList = new ArrayList<String>();

	@JsonProperty("identificationList")
	private List<String> cedulaIdentidadList = new ArrayList<String>();

	@JsonProperty("genderList")
	private List<String> sexoList = new ArrayList<String>();

	@JsonProperty("birthdate")
	private String fechaNacimiento;

	@JsonProperty("locationProvinceList")
	private List<String> localidadProvinciaList = new ArrayList<String>();

	@JsonProperty("phoneList")
	private List<String> telefonoList = new ArrayList<String>();

	@JsonProperty("cellCompanyList")
	private List<String> companiaCelularList = new ArrayList<String>();

	@JsonProperty("emailList")
	private List<String> emailList = new ArrayList<String>();


	public List<String> getNombreApellidoList() {
		return nombreApellidoList;
	}

	public void setNombreApellidoList(List<String> nombreApellidoList) {
		this.nombreApellidoList = nombreApellidoList;
	}

	public List<String> getSeguroMedicoList() {
		return seguroMedicoList;
	}

	public void setSeguroMedicoList(List<String> seguroMedicoList) {
		this.seguroMedicoList = seguroMedicoList;
	}

	public List<String> getcedulaIdentidadList() {
		return cedulaIdentidadList;
	}

	public void setcedulaIdentidadList(List<String> cedulaIdentidadList) {
		this.cedulaIdentidadList = cedulaIdentidadList;
	}

	public List<String> getSexoList() {
		return sexoList;
	}

	public void setSexoList(List<String> sexoList) {
		this.sexoList = sexoList;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public List<String> getLocalidadProvinciaList() {
		return localidadProvinciaList;
	}

	public void setLocalidadProvinciaList(List<String> localidadProvinciaList) {
		this.localidadProvinciaList = localidadProvinciaList;
	}

	public List<String> getTelefonoList() {
		return telefonoList;
	}

	public void setTelefonoList(List<String> telefonoList) {
		this.telefonoList = telefonoList;
	}

	public List<String> getCompaniaCelularList() {
		return companiaCelularList;
	}

	public void setCompaniaCelularList(List<String> companiaCelularList) {
		this.companiaCelularList = companiaCelularList;
	}

	public List<String> getEmailList() {
		return emailList;
	}

	public void setEmailList(List<String> emailList) {
		this.emailList = emailList;
	}
	
	
	/**
	 * Prepara los filtros para la búsqueda
	 */
//	public void normalizeFilters() {
//		
//		if(this.nombreApellidoList.size() == 0) {
//			this.nombreApellidoList.add("-1");
//		}
//		else for(int i = 0; i < this.nombreApellidoList.size(); i++) {
//			String nombreApellido = this.nombreApellidoList.get(i);
//			
//			this.nombreApellidoList.set(i, element)
//		}
//	}
}

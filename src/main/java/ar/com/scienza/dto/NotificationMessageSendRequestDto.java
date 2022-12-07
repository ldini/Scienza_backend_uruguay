package ar.com.scienza.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NotificationMessageSendRequestDto extends BodyDto {

	@Valid
	@JsonProperty("idAffiliateList")
	private List<Integer> afiliadoIdList = new ArrayList<Integer>();

	@NotNull(message="massive {required}")
	@JsonProperty("massive")
	private Boolean massive;

	@JsonProperty("fileName")
	private String fileName;

	@JsonProperty("content")
	private String content;

	@JsonProperty("contentType")
	private String contentType;

	@NotEmpty(message="title {required}")
	@JsonProperty("title")
	private String titulo;

	@NotEmpty(message="message {required}")
	@JsonProperty("message")
	private String mensaje;


	public List<Integer> getAfiliadoIdList() {
		return afiliadoIdList;
	}

	public void setAfiliadoIdList(List<Integer> afiliadoIdList) {
		this.afiliadoIdList = afiliadoIdList;
	}

	public Boolean getMassive() {
		return massive;
	}

	public void setMassive(Boolean massive) {
		this.massive = massive;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
}

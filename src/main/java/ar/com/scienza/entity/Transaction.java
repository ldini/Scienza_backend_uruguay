package ar.com.scienza.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import ar.com.scienza.base.ScienzaEntity;

@Component
@RequestScope
@Entity
@Table(name="transaccion")
public class Transaction extends ScienzaEntity {

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "sesion_id", referencedColumnName = "id", nullable = false)
	private Sesion sesion;
	
	@Column(name="controller", length = 100, nullable = true)
	private String controller;

	@Column(name="action", length = 100, nullable = true)
	private String action;

	@Column(name="endpoint", length = 255, nullable = true)
	private String endpoint;

	@Column(name="request", length = 1024, nullable = true)
	private String request;

	@Column(name="response", length = 2048, nullable = true)
	private String response;

	
	public Sesion getSesion() {
		return sesion;
	}

	public void setSesion(Sesion sesion) {
		this.sesion = sesion;
	}

	public String getController() {
		return controller;
	}

	public void setController(String controller) {
		this.controller = controller;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}
}

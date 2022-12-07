package ar.com.scienza.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import ar.com.scienza.base.ScienzaEntity;


@Entity
@Table(name="email")
public class Email extends ScienzaEntity { 

	@Column(name="tipo_email", nullable = false, length = 10)
	private String tipoEmail;
	
	@Column(name="subject", nullable = false, length = 100)
	private String subject;

	@Column(name="signature", nullable = true, length = 100)
	private String signature;

	@Column(name="recipient_to", nullable = false, length = 500)
	private String TO;

	@Column(name="recipient_cc", nullable = true, length = 500)
	private String CC;
	
	@Column(name="recipient_cco", nullable = true, length = 500)
	private String CCO;

	
	public String getTipoEmail() {
		return tipoEmail;
	}

	public void setTipoEmail(String tipoEmail) {
		this.tipoEmail = tipoEmail;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getTO() {
		return TO;
	}

	public void setTO(String tO) {
		TO = tO;
	}

	public String getCC() {
		return CC;
	}

	public void setCC(String cC) {
		CC = cC;
	}

	public String getCCO() {
		return CCO;
	}

	public void setCCO(String cCO) {
		CCO = cCO;
	}
}

package ar.com.scienza.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import ar.com.scienza.base.ScienzaEntity;

@Entity
@Table(name="pregunta_frecuente")
public class PreguntaFrecuente extends ScienzaEntity {

	@Column(name="categoria", nullable = false, length = 200)
	private String categoria;

	@Column(name="pregunta", nullable = false, length = 200)
	private String pregunta;

	@Column(name="respuesta", nullable = false, length = 500)
	private String respuesta;

	
	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getPregunta() {
		return pregunta;
	}

	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}

	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
}

package ar.com.scienza.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ar.com.scienza.base.ScienzaEntity;
import ar.com.scienza.utils.ParserUtil;

@Entity
@Table(name="notificacion")
public class Notificacion extends ScienzaEntity {

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "afiliado_id", referencedColumnName = "id", nullable = false)
	private Afiliado afiliado;

	@Column(name="tipo_notificacion", length = 20, nullable = false)
	private String tipoNotificacion;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "mensaje_id", referencedColumnName = "id", nullable = false)
	private Mensaje mensaje;
	
	@Column(name="notificado", columnDefinition ="BIT", length = 1, nullable = false)
	private Boolean notificado;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_notificado", nullable = true)
    private Date fechaNotificado;
	
	@Column(name="leido", columnDefinition ="BIT", length = 1, nullable = false)
	private Boolean leido;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_leido", nullable = true)
    private Date fechaLeido;

	@Column(name="entity_id", nullable = true)
	private Integer entityId;

	
	public Afiliado getAfiliado() {
		return afiliado;
	}

	public void setAfiliado(Afiliado afiliado) {
		this.afiliado = afiliado;
	}

	public String getTipoNotificacion() {
		return tipoNotificacion;
	}

	public void setTipoNotificacion(String tipoNotificacion) {
		this.tipoNotificacion = tipoNotificacion;
	}

	public Mensaje getMensaje() {
		return mensaje;
	}

	public void setMensaje(Mensaje mensaje) {
		this.mensaje = mensaje;
	}

	public Boolean getNotificado() {
		return notificado;
	}

	public void setNotificado(Boolean notificado) {
		this.notificado = notificado;
	}

	public Date getFechaNotificado() {
		return fechaNotificado;
	}

	public void setFechaNotificado(Date fechaNotificado) {
		this.fechaNotificado = fechaNotificado;
	}

	public Boolean getLeido() {
		return leido;
	}

	public void setLeido(Boolean leido) {
		this.leido = leido;
	}

	public Date getFechaLeido() {
		return fechaLeido;
	}

	public void setFechaLeido(Date fechaLeido) {
		this.fechaLeido = fechaLeido;
	}
		
	public Integer getEntityId() {
		return entityId;
	}

	public void setEntityId(Integer entityId) {
		this.entityId = entityId;
	}


	
	

	/**
	 * Procesa y devuelve el Mensaje PUSH
	 * @return
	 */
	public String processMensajePush() {
		return ParserUtil.parseMessage(this.getAfiliado(), this.getMensaje().getMensajePush());
	}

	
	/**
	 * Procesa y devuelve el Mensaje Abreviado
	 * @return
	 */
	public String processMensajeAbreviado() {
		return ParserUtil.parseMessage(this.getAfiliado(), this.getMensaje().getMensajeAbreviado());
	}

	
	/**
	 * Procesa y devuelve el Mensaje
	 * @return
	 */
	public String processMensaje() {
		return ParserUtil.parseMessage(this.getAfiliado(), this.getMensaje().getMensaje());
	}


	/**
	 * Procesa y devuelve el Titulo
	 * @return
	 */
	public String processTitulo() {
		return ParserUtil.parseMessage(this.getAfiliado(), this.getMensaje().getTitulo());
	}
}

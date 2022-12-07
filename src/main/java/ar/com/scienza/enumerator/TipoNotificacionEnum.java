package ar.com.scienza.enumerator;

public enum TipoNotificacionEnum {

	PERFIL_ACEPTADO ("PROFILE_ACCEPTED", "Perfil Aceptado por el Administrador"),
	PERFIL_RECHAZADO ("PROFILE_REJECTED", "Perfil Rechazado por el Administrador"),
	MENSAJE_ADMIN ("MENSAJE_ADMIN", "Notificación del Administrador"),
	COOORD_ENTREGA ("COOORD_ENTREGA", "Coordinación Entrega desde SAP"),
	DISTRIB_ENTREGA ("DISTRIB_ENTREGA", "Inicio de distribución de Entrega"),
	CERCANIA_ENTREGA ("CERCANIA_ENTREGA", "Cercanía de distribución de Entrega"),
	RTA_CONSULTA_AFILIADO ("RTA_CONSULTA_AFILIADO", "Respuesta Afiliado sobre una consulta"),
	RTA_CONSULTA_ADMIN ("RTA_CONSULTA_ADMIN", "Respuesta Administrador sobre una consulta"),
	CONSULTA_NUEVA ("CONSULTA_NUEVA", "Nueva consulta de un afiliado"),
	CONSULTA_ASIGNADA ("CONSULTA_ASIGNADA", "Consulta tomada por un administrador"),
	@Deprecated
	RESPUESTA_CONSULTA ("RTA_CONSULTA", "Respuesta de consulta de Admin");
	
	private String codigo;

	private String descripcion;
	

	private TipoNotificacionEnum(String codigo, String descripcion) {
		this.codigo = codigo;
		this.descripcion = descripcion;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}

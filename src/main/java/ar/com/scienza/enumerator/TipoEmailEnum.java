package ar.com.scienza.enumerator;

public enum TipoEmailEnum {

	RECETA_MEDICA  ("RECMED", "Receta Medica"),
	OTROS_ESTUDIOS ("OTREST", "Otros Estudios"),
	ERROR_VINCULACION("ERRORVIN", "Error de vinculación"),
	DATOS_VALIDACION("VALDAT", "Validación de datos"),
	DATOS_SOLICITUD("SOLDAT", "Consulta Inicio"),
	DATOS_FINALIZACION("FINDAT", "Consulta Finalizada"),
	TRAMITE_SOLICITUD("TRADAT", "Solicitudes"),
	DOCUMENTACION_SOLICITUD("DOCDAT", "Documentación Inicio");



	private String codigo;

	private String descripcion;
	

	private TipoEmailEnum(String codigo, String descripcion) {
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

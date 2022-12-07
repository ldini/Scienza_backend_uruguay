	package ar.com.scienza.enumerator;

public enum ConsultaMensajeEnum {

	PENDIENTE	  ("MESSAGE", "Esperando un representante", "Su solicitud fue generada con éxito. Un representante le responderá a la brevedad."),
	NO_OPERATIVO  ("MESSAGE", "Esperando un representante", "Su solicitud fue generada con éxito. Un representante le responderá a la brevedad."),
	FUERA_HORARIO ("MESSAGE", "Esperando un representante", "Su solicitud fue generada con éxito. Le informamos que el horario de atención online es de lunes a viernes de 08 hs a 17:30 hs."),
	EN_CURSO   	  ("ASSIGN", "Está conversando con ADMIN", null),
	FINALIZADO 	  ("FINISH", "Estuvo conversando con ADMIN", "Solicitud finalizada."),
	ERROR	   	  ("ERROR", "Estuvo conversando con ADMIN", "Se desconoce la información enviada. Por razones de seguridad se finaliza la conversación.");
	
	private String codigo;

	private String titulo;
	
	private String descripcion;


	private ConsultaMensajeEnum(String codigo, String titulo, String descripcion) {
		this.codigo = codigo;
		this.titulo = titulo;
		this.descripcion = descripcion;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}

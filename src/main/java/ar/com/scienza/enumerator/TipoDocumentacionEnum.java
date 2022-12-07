package ar.com.scienza.enumerator;

public enum TipoDocumentacionEnum {

	RECETA_MEDICA ("RECMED", "Receta Medica"),
	OTROS_ESTUDIOS ("OTREST", "Otros Estudios");
	
	private String codigo;

	private String descripcion;
	

	private TipoDocumentacionEnum(String codigo, String descripcion) {
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

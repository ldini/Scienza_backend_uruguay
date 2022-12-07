package ar.com.scienza.enumerator;

public enum SAPResultadoEnum {

	STATUS_OK 	 ("OK", "Status OK"),
	STATUS_ERROR ("NO_OK", "Status NO OK");
	
	private String codigo;

	private String descripcion;
	

	private SAPResultadoEnum(String codigo, String descripcion) {
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

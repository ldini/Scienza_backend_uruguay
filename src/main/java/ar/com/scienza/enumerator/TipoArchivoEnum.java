package ar.com.scienza.enumerator;


public enum TipoArchivoEnum {

	IMAGEN ("IMG", "Imagen"),
	PDF	   ("PDF", "PDF");
	
	private String codigo;

	private String descripcion;
	

	private TipoArchivoEnum(String codigo, String descripcion) {
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

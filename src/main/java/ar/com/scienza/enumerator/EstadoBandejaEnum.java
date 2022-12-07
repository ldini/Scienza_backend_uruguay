package ar.com.scienza.enumerator;

public enum EstadoBandejaEnum {

	PENDIENTE ("P", "Pendiente"),
	EN_CURSO ("C", "En Curso"),
	APROBADO  ("A", "Aprobado"),
	RECHAZADO ("R", "Rechazado");
	
	private String codigo;

	private String descripcion;
	

	private EstadoBandejaEnum(String codigo, String descripcion) {
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

package ar.com.scienza.enumerator;

public enum RolEnum {

	ADMIN ("ADMIN", "Administrador Scienza"),
	AFILIADO ("AFILIA", "Afiliado Scienza");
	
	private String codigo;

	private String descripcion;
	

	private RolEnum(String codigo, String descripcion) {
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

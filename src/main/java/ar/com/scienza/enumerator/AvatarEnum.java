package ar.com.scienza.enumerator;


public enum AvatarEnum {

	AVATAR_INDIS ("INDIS", "Avatar Indistinto"),
	AVATAR_ADMIN ("ADMIN", "Avatar Admin"),
	AVATAR_MAN 	 ("MAN", "Avatar Afiliado Hombre"),
	AVATAR_WOMAN ("WOMAN", "Avatar Afiliado Mujer"),
	AVATAR_BOY 	 ("BOY", "Avatar Afiliado Nene"),
	AVATAR_GIRL  ("GIRL", "Avatar Afiliado Nena");
	
	private String codigo;

	private String descripcion;
	

	private AvatarEnum(String codigo, String descripcion) {
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

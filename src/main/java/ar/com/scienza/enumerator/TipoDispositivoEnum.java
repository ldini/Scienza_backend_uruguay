package ar.com.scienza.enumerator;

import java.util.Arrays;

public enum TipoDispositivoEnum {

	ANDROID ("AND", "Dispositivo Android"),
	IOS ("IOS", "Dispositivo IOS"),
	WEB ("WEB", "Dispositivo WEB");
	
	private String codigo;

	private String descripcion;
	

	private TipoDispositivoEnum(String codigo, String descripcion) {
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
	
	
	public static boolean containsCode(String code) {
		return Arrays.asList(ANDROID.getCodigo(), IOS.getCodigo(), WEB.getCodigo()).contains(code);
	}
}

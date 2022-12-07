package ar.com.scienza.enumerator;


public enum TipoTurnoEnum {

	MANIANA ("M", "Mañana", "MAÑANA"),
	TARDE ("T", "Tarde", "TARDE"),
	NOCHE ("N", "Noche", "NOCHE");
	
	private String codigo;

	private String descripcion;
	
	private String codigoSAP;
	

	private TipoTurnoEnum(String codigo, String descripcion, String codigoSAP) {
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.codigoSAP = codigoSAP;
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
	
	public String getCodigoSAP() {
		return codigoSAP;
	}

	public void setCodigoSAP(String codigoSAP) {
		this.codigoSAP = codigoSAP;
	}

	
	/**
	 * Obtiene el enumerador del medio de pago
	 * @param value
	 * @return
	 * @throws Exception 
	 */
	public static TipoTurnoEnum valueSAP(String value) throws Exception {

		if(value == null)
			return null;
		
		switch (value) {
			case "MAÑANA":
				return TipoTurnoEnum.MANIANA;
				
			case "TARDE":
				return TipoTurnoEnum.TARDE;
				
			case "NOCHE":
				return TipoTurnoEnum.NOCHE;
				
			default:
				throw new Exception("Tipo de Turno desconocido: " + value);
				
		}
	}
}

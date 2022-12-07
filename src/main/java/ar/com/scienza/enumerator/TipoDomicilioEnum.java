package ar.com.scienza.enumerator;


public enum TipoDomicilioEnum {

	AFILIADO ("AFI", "Afiliado", "AFILIADO"),
	FARMACIA ("FAR", "Farmacia", "FARMACIA");
	
	private String codigo;

	private String descripcion;
	
	private String codigoSAP;
	

	private TipoDomicilioEnum(String codigo, String descripcion, String codigoSAP) {
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
	public static TipoDomicilioEnum valueSAP(String value) throws Exception {

		if(value == null)
			return null;
		
		switch (value) {
			case "AFILIADO":
				return TipoDomicilioEnum.AFILIADO;
				
			case "FARMACIA":
				return TipoDomicilioEnum.FARMACIA;
				
			default:
				throw new Exception("Tipo Domicilio desconocido: " + value);
				
		}
	}
}

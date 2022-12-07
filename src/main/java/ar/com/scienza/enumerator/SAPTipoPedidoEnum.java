package ar.com.scienza.enumerator;

public enum SAPTipoPedidoEnum {

	EN_CURSO  ("EN_CURSO", "Pedido EN CURSO"),
	HISTORICO ("HISTORICO", "Pedido HISTORICO");
	
	private String codigo;

	private String descripcion;
	

	private SAPTipoPedidoEnum(String codigo, String descripcion) {
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
	
	

	/**
	 * Obtiene el enumerador del tipo de pedido
	 * @param value
	 * @return
	 * @throws Exception 
	 */
	public static SAPTipoPedidoEnum valueSAP(String value) throws Exception {
		
		if(value == null)
			return null;
		
		// Fix provisorio no interpreta acentos
		if(value.contains("HIST"))
			return SAPTipoPedidoEnum.HISTORICO;
		
		switch (value) {
			case "EN CURSO":
				return SAPTipoPedidoEnum.EN_CURSO;
				
			case "HISTÓRICO":
				return SAPTipoPedidoEnum.HISTORICO;
				
			default:
				throw new Exception("Tipo de Pedido desconocido: " + value);
				
		}
	}
}

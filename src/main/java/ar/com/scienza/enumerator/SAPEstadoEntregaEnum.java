package ar.com.scienza.enumerator;

import java.util.Arrays;

public enum SAPEstadoEntregaEnum {

	EN_PREPARACION	("EN_PREPARACION", "En preparación"),
	EN_DISTRIBUCION	("EN_DISTRIBUCION", "En distribución"),
	ENTREGADO	 	("ENTREGADO", "Entregado"),
	RECOORDINAR 	("EN_PREPARACION", "Recoordinar"),
	DEVOLUCION 		("EN_PREPARACION", "Devolución"),
	EN_FARMACIA 	("EN_PREPARACION", "En Farmacia"),
	NO_MOSTRAR 		("NO_MOSTRAR", "No mostrar");
	
	private String codigo;

	private String descripcion;
	

	private SAPEstadoEntregaEnum(String codigo, String descripcion) {
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
	 * Obtiene el enumerador del estado de entrega
	 * @param value
	 * @return
	 * @throws Exception 
	 */
	public static SAPEstadoEntregaEnum valueSAP(String value) throws Exception {
		
		if(value == null)
			return null;
		
		switch (value) {
			case "EN PREPARACION":
				return SAPEstadoEntregaEnum.EN_PREPARACION;
				
			case "EN DISTRIBUCION":
				return SAPEstadoEntregaEnum.EN_DISTRIBUCION;
				
			case "ENTREGADO":
				return SAPEstadoEntregaEnum.ENTREGADO;

			case "FINALIZADO":
				return SAPEstadoEntregaEnum.ENTREGADO;
				
			case "RECOORDINAR":
				return SAPEstadoEntregaEnum.RECOORDINAR;
				
			case "DEVOLUCION":
				return SAPEstadoEntregaEnum.DEVOLUCION;
				
			case "EN FARMACIA":
				return SAPEstadoEntregaEnum.EN_FARMACIA;
				
			case "NO MOSTRAR":
				return SAPEstadoEntregaEnum.NO_MOSTRAR;
				
			default:
				throw new Exception("Estado de Entrega desconocido: " + value);
				
		}
	}

	
	/**
	 * Determina los estados de entrega que sean visibles
	 * @param estadoEntrega
	 * @return
	 */
	public static boolean isValid(SAPEstadoEntregaEnum estadoEntrega) {
		
		return Arrays.asList(EN_PREPARACION.getCodigo(), EN_DISTRIBUCION.getCodigo(), ENTREGADO.getCodigo(), RECOORDINAR.getCodigo()).contains(estadoEntrega.getCodigo());
	}
}

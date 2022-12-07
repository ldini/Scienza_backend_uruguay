package ar.com.scienza.enumerator;

import java.util.Arrays;

public enum SAPEstadoPedidoEnum {

	REVISION  		("REVISION", "En revisión"),
	AUDITORIA 		("AUDITORIA", "En auditoría"),
	PARA_COORDINAR 	("PARA_COORDINAR", "Para coordinar"),
	PARA_COORDINAR_BLOQUEADO ("AUDITORIA", "En proceso"),  // Se hace esto porque la coordinacion es manual
	PARA_COORDINAR_VIEJO ("AUDITORIA", "Para Coordinar"),
	COORDINADO 		("COORDINADO", "Coordinado"),
	NO_MOSTRAR 		("NO_MOSTRAR", "No Mostrar"),
	ENTREGA_PARCIAL ("ENTREGA_PARCIAL", "Entregado parcial"),
	FINALIZADO 		("ENTREGADO", "Entregado");
	
	private String codigo;

	private String descripcion;
	

	private SAPEstadoPedidoEnum(String codigo, String descripcion) {
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
	 * Obtiene el enumerador del estado de pedido
	 * @param value
	 * @return
	 * @throws Exception 
	 */
	public static SAPEstadoPedidoEnum valueSAP(String value) throws Exception {
		
		if(value == null)
			return null;
		
		switch (value) {
			case "EN REVISION":
				return SAPEstadoPedidoEnum.REVISION;
				
			case "EN AUDITORIA":
				return SAPEstadoPedidoEnum.AUDITORIA;
				
			case "PARA COORDINAR":
				return SAPEstadoPedidoEnum.PARA_COORDINAR;
				
			case "COORDINADO":
				return SAPEstadoPedidoEnum.COORDINADO;
				
			case "NO MOSTRAR":
				return SAPEstadoPedidoEnum.NO_MOSTRAR;
				
			case "ENTREGADO PARCIAL":
				return SAPEstadoPedidoEnum.ENTREGA_PARCIAL;
				
			case "ENTREGADO":
				return SAPEstadoPedidoEnum.FINALIZADO;
				
			case "FINALIZADO":
				return SAPEstadoPedidoEnum.FINALIZADO;
				
			default:
				throw new Exception("Estado de Pedido desconocido: " + value);
				
		}
	}

	
	/**
	 * Determina los estados del pedido que sean visibles
	 * @param
	 * @return
	 */
	public static boolean isValid(SAPEstadoPedidoEnum estadoPedido) {
		
		return Arrays.asList(
				REVISION.getCodigo(), 
				AUDITORIA.getCodigo(), 
				PARA_COORDINAR.getCodigo(),
				COORDINADO.getCodigo(),
				ENTREGA_PARCIAL.getCodigo(),
				FINALIZADO.getCodigo()
			)
			.contains(estadoPedido.getCodigo());
	}
}

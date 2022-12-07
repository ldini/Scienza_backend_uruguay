package ar.com.scienza.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.scienza.entity.Pedido;


public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

	/**
	 * Busca un pedido de un afiliado
	 * @param afiliadoId
	 * @param numeroPedido
	 * @return
	 */
	public Pedido findByAfiliadoIdAndNumeroPedido(Integer afiliadoId, Integer numeroPedido);
}
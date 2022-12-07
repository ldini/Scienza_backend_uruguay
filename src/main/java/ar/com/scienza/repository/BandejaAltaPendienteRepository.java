package ar.com.scienza.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.scienza.entity.BandejaAltaPendiente;


public interface BandejaAltaPendienteRepository extends JpaRepository<BandejaAltaPendiente, Integer> {


	/**
	 * Busca las altas pendientes de vinculación
	 * @param estado
	 * @return
	 */
	public List<BandejaAltaPendiente> findByEstadoOrderByFechaErrorAsc(String estado);
}

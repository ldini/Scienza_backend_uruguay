package ar.com.scienza.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.scienza.entity.BandejaPerfilPendiente;


public interface BandejaPerfilPendienteRepository extends JpaRepository<BandejaPerfilPendiente, Integer> {

	/**
	 * Busca si existen verificaciones pendientes por el admin
	 * @param estado
	 * @return
	 */
	public Long countByEstadoAndAfiliadoId(String estado, Integer afiliadoId);
	

	/**
	 * Busca los perfiles pendientes de verificación
	 * @param estado
	 * @return
	 */
	public List<BandejaPerfilPendiente> findByEstadoOrderByFechaEdicionAsc(String estado);

}

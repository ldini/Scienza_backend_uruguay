package ar.com.scienza.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.scienza.entity.ConsultaSesion;


public interface ConsultaSesionRepository extends JpaRepository<ConsultaSesion, Integer> {

	/**
	 * Busca la suscripcion mediante el token
	 * @param token
	 * @return
	 */
	public ConsultaSesion findByConsultaCanalAndSesionToken(String canal, String token);
	
	/**
	 * Busca los administradores de un canal
	 * @param canal
	 * @return
	 */
	public List<ConsultaSesion> findAllByConsultaCanalAndSesionAdministradorIsNotNull(String canal);

	/**
	 * Busca los afiliados de un canal
	 * @param canal
	 * @return
	 */
	public List<ConsultaSesion> findAllByConsultaCanalAndSesionAfiliadoIsNotNull(String canal);
}
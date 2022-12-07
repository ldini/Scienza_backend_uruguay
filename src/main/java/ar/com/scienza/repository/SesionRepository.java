package ar.com.scienza.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ar.com.scienza.entity.Sesion;


public interface SesionRepository extends JpaRepository<Sesion, Integer> {

	/**
	 * Busca una sesion por su token
	 * @param token
	 * @return
	 */
	public Sesion findByToken(String token);
	

	/**
	 * Busca sesiones que coincidan su playerId
	 * @param token
	 * @return
	 */
	public List<Sesion> findAllByPlayerId(String playerId);
	

	/**
	 * Busca los Players del Afilado Principal o Vinculado
	 * @param token
	 * @return
	 */
	@Query("SELECT s.playerId FROM Sesion s WHERE (s.afiliado.id = ?1 OR s.afiliado.id IN(	"
		 + "	SELECT a.id FROM Afiliado a JOIN a.afiliadosVinculados av WHERE av.id = ?1	"
		 + ")) AND s.playerId IS NOT NULL													"
	)
	public List<String> findPlayersByAffiliate(Integer afiliadoId);
	

	/**
	 * Busca sesiones del Afilado Principal o Vinculado
	 * @param token
	 * @return
	 */
	@Query("SELECT s FROM Sesion s WHERE (s.afiliado.id = ?1 OR s.afiliado.id IN(	"
		 + "	SELECT a.id FROM Afiliado a JOIN a.afiliadosVinculados av WHERE av.id = ?1	"
		 + "))													"
	)
	public List<Sesion> findSesionByAffiliate(Integer afiliadoId);


	/**
	 * Busca los Players del Afilado Principal o Vinculado
	 * @param token
	 * @return
	 */
	@Query("SELECT s FROM Sesion s WHERE (s.afiliado.id = ?1 OR s.afiliado.id IN(	"
		 + "	SELECT a.id FROM Afiliado a JOIN a.afiliadosVinculados av WHERE av.id = ?1	"
		 + ")) AND s.playerId IS NOT NULL AND s.fechaDelete IS NULL							"
	)
	public List<Sesion> findSesionByAffiliateWithPlayerId(Integer afiliadoId);


	/**
	 * Busca los Players del Administrador
	 * @param token
	 * @return
	 */
	@Query("SELECT s.playerId FROM Sesion s WHERE s.administrador.id = ?1 AND s.playerId IS NOT NULL")
	public List<String> findPlayersByAdmin(Integer adminId);


	/**
	 * Busca los Players del Administrador
	 * @param token
	 * @return
	 */
	@Query("SELECT s FROM Sesion s WHERE s.administrador.id = ?1 AND s.playerId IS NOT NULL")
	public List<Sesion> findSesionByAdmin(Integer adminId);
}
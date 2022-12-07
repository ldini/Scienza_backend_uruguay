package ar.com.scienza.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ar.com.scienza.entity.Afiliado;
import ar.com.scienza.transformer.EntityTRF;


public interface AfiliadoRepository extends JpaRepository<Afiliado, Integer>, AfiliadoRepositoryCustom {

	/**
	 * Busca un afiliado por nro documento y id sap
	 * @param cedulaIdentidad
	 * @param sapId
	 * @return
	 */
	public Afiliado findBycedulaIdentidadAndSapId(String cedulaIdentidad, Long sapId);

	/**
	 * Busca un afiliado por id sap
	 * @param
	 * @param sapId
	 * @return
	 */
	public Afiliado findBySapId(Long sapId);
	
	
	/**
	 * Busca un afiliado por su usuario
	 * @param usuarioId
	 * @return
	 */
	public Afiliado findByUsuarioId(Integer usuarioId);

	
	/**
	 * Obtiene la lista de provincias
	 * @return
	 */
	@Query("SELECT new ar.com.scienza.transformer.EntityTRF(CONCAT('P-', MIN(a.id)), a.provincia) FROM Afiliado a "
		 + "GROUP BY a.provincia ORDER BY a.provincia ASC")
	public List<EntityTRF> findAllProvincias();

	
	/**
	 * Obtiene la lista de localidades
	 * @return
	 */
	@Query("SELECT new ar.com.scienza.transformer.EntityTRF(CONCAT('L-', MIN(a.id)), CONCAT(a.localidad, ' (', a.provincia, ')')) FROM Afiliado a "
		 + "GROUP BY a.localidad, a.provincia ORDER BY a.provincia ASC, a.localidad ASC")
	public List<EntityTRF> findAllLocalidades();

	/*
    Obtiene a lista de afiliados que recibieron un mensaje determinado.
     */
	@Query("SELECT a FROM Afiliado a WHERE a.id IN(SELECT n.afiliado.id FROM Notificacion n WHERE n.mensaje.id = ?1)")
	public List<Afiliado> findAllByMensaje(Integer idMensaje);
}

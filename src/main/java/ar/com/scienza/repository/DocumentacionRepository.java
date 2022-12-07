package ar.com.scienza.repository;

import java.util.List;

import ar.com.scienza.transformer.DocumentacionTRF;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ar.com.scienza.entity.Documentacion;


public interface DocumentacionRepository extends JpaRepository<Documentacion, Integer> {

	/**
	 * Obtiene los documentos de un tipo determinado para un afiliado
	 * @param afiliadoId
	 * @param tipoDocumentacion
	 * @return
	 */
	@Query("SELECT d FROM Documentacion d "
		 + "WHERE d.afiliado.id = ?1 AND d.tipoDocumentacion = ?2 AND d.fechaDelete IS NULL "
		 + "ORDER BY d.fechaInsert DESC"
	)
	public List<Documentacion> findAllByAfiliadoIdAndTipoDocumentacion(Integer afiliadoId, String tipoDocumentacion);
	

	/**
	 * Obtiene la cantidad de documentaciones subidas subidos
	 * @param
	 * @return
	 */
	public Long countByAfiliadoIdAndTipoDocumentacionAndFechaDeleteIsNull(Integer afiliadoId, String tipoDocumentacion);


	@Query("SELECT d FROM Documentacion d "
			+ "WHERE d.afiliado.id = ?1 AND d.fechaDelete IS NULL "
			+ "ORDER BY d.fechaInsert DESC"
	)
	public List<Documentacion> findAllByAfiliadoId(Integer afiliadoId);

	/**
	 * Obtiene la cantidad de documentaciones subidas subidos
	 * @param
	 * @return
	 */
	public Long countByAfiliadoIdAndFechaDeleteIsNull(Integer afiliadoId);

	public Documentacion findOneByIdAndAfiliadoId(Integer documentationId, Integer afiliadoId);

	@Query("SELECT COUNT(*) FROM Documentacion d "
			+ " WHERE d.afiliado.id = ?1"
	)
	public Long countByDocumentacion(Integer afiliadoId);

	@Query("SELECT new ar.com.scienza.transformer.DocumentacionTRF(d.id , d.fechaInsert) "
			+ " FROM Documentacion d"
			+ " WHERE d.afiliado.id = ?1 AND d.fechaDelete IS NULL "
			+ "ORDER BY d.fechaInsert DESC"

	)
	public List<DocumentacionTRF> findAllTRFByAfiliadoId(Integer afiliadoId);
}
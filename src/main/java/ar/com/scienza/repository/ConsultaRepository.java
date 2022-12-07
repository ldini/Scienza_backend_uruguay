package ar.com.scienza.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ar.com.scienza.entity.Consulta;
import ar.com.scienza.transformer.ConsultaTRF;


public interface ConsultaRepository extends JpaRepository<Consulta, Integer> {

	/**
	 * Obtiene una consulta a partir de su canal
	 * @param channel
	 * @return
	 */
	public Consulta findByCanal(String channel);
	
	
	/**
	 * Obtiene la lista de consultas para un afiliado
	 * @param afiliadoId
	 * @return
	 */
	@Query("SELECT new ar.com.scienza.transformer.ConsultaTRF(c.id, MAX(cm.fechaInsert)) "
		+ " FROM Consulta c JOIN c.consultaMensajeList cm "
		+ " WHERE c.afiliado.id = ?1 AND c.fechaDelete IS NULL "
		+ " GROUP BY c.id "
		+ " ORDER BY MAX(cm.fechaInsert) DESC"
	)
	public List<ConsultaTRF> findAllTRFByAfiliadoId(Integer afiliadoId);
	
	
	/**
	 * Busca una consulta por su afiliado
	 * @param consultaId
	 * @param afiliadoId
	 * @return
	 */
	public Consulta findOneByIdAndAfiliadoId(Integer consultaId, Integer afiliadoId);

	
	/**
	 * Obtiene la cantidad de consultas no leidas
	 * @param estado
	 * @return
	 */
	@Query("SELECT COUNT(*) FROM BandejaConsultaPendiente bcp JOIN bcp.consulta c JOIN c.consultaMensajeList cm "
		+ " WHERE c.afiliado.id = ?1 AND cm.administrador IS NOT NULL AND cm.leido = false AND cm.fechaDelete IS NULL"
	)
	public Long countByConsultasNoLeidas(Integer afiliadoId);

}
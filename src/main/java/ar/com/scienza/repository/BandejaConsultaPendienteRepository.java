package ar.com.scienza.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ar.com.scienza.entity.BandejaConsultaPendiente;


public interface BandejaConsultaPendienteRepository extends JpaRepository<BandejaConsultaPendiente, Integer> {

	/**
	 * Busca las consultas mediante un estado
	 * @param estado
	 * @return
	 */
	@Query("SELECT bcp FROM BandejaConsultaPendiente bcp WHERE bcp.estado = ?1 AND bcp.fechaDelete IS NULL "
		+ " ORDER BY bcp.fechaConsulta ASC"
	)
	public List<BandejaConsultaPendiente> findAllByEstado(String estado);
	

	/**
	 * Busca las consultas mediante un estado y un administrador
	 * @param estado
	 * @return
	 */
	@Query("SELECT bcp FROM BandejaConsultaPendiente bcp WHERE bcp.estado = ?1 AND bcp.administrador.id = ?2 AND bcp.fechaDelete IS NULL "
		+ " ORDER BY bcp.fechaConsulta ASC"
	)
	public List<BandejaConsultaPendiente> findAllByEstadoAndAdministrator(String estado, Integer adminId);
	

	/**
	 * Busca las consultas mediante un estado y un administrador
	 * @param estado
	 * @return
	 */
	@Query("SELECT bcp FROM BandejaConsultaPendiente bcp JOIN bcp.consulta c WHERE bcp.estado = ?1 AND c.afiliado.id = ?2 AND bcp.fechaDelete IS NULL "
		+ " ORDER BY bcp.fechaConsulta DESC"
	)
	public List<BandejaConsultaPendiente> findAllByEstadoAndAfiliado(String estado, Integer afiliadoId);
	
	
	/**
	 * Consultas Historicas
	 * @return
	 */
	@Query("SELECT bcp FROM BandejaConsultaPendiente bcp JOIN bcp.consulta c JOIN c.afiliado a "
		+ " WHERE bcp.estado = ?1 "
		+ " AND (?2 IS NULL OR c.ticket = ?2) AND (?3 IS NULL OR a.sapId = ?3) AND (?4 = '' OR a.nombre = ?4) AND ( ?5 ='' OR a.apellido = ?5) "
		+ " AND bcp.fechaDelete IS NULL "
		+ " ORDER BY bcp.fechaConsulta DESC "
	)
	public List<BandejaConsultaPendiente> findAllConsultasHistoricas(String estado, Integer ticket, Long sapId , String firstName , String lastName);


	/**
	 * Consultas Historicas
	 * @return
	 */
	public List<BandejaConsultaPendiente> findTop500ByEstadoAndFechaDeleteIsNullOrderByFechaConsultaDesc(String estado);
	

	/**
	 * Busca todos los registros con un estado determinado
	 * @param status
	 * @return
	 */
	public List<BandejaConsultaPendiente> findAllByEstadoInAndFechaDeleteIsNull(List<String> status);
	

	/**
	 * Busca La Bandeja de una Consulta
	 * @param
	 * @return
	 */
	public BandejaConsultaPendiente findByConsultaId(Integer consultaId);
}

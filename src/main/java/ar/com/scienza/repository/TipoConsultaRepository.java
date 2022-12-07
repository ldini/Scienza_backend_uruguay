package ar.com.scienza.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.scienza.entity.TipoConsulta;


public interface TipoConsultaRepository extends JpaRepository<TipoConsulta, Integer> {

	/**
	 * Busca todos los tipos de consulta habilitados
	 * @return
	 */
	public List<TipoConsulta> findAllByFechaDeleteIsNullOrderByIdAsc();
}
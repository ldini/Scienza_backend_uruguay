package ar.com.scienza.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ar.com.scienza.entity.PreguntaFrecuente;


public interface PreguntaFrecuenteRepository extends JpaRepository<PreguntaFrecuente, Integer> {

	/**
	 * Busca las categorias de preguntas frecuentes
	 * @param token
	 * @return
	 */
	@Query("SELECT DISTINCT pf.categoria FROM PreguntaFrecuente pf WHERE pf.fechaDelete IS NULL")
	public List<String> findCategorias();
	

	/**
	 * Busca las preguntas frecuentes
	 * @param token
	 * @return
	 */
	public List<PreguntaFrecuente> findAllByCategoriaAndFechaDeleteIsNull(String categoria);
	
}
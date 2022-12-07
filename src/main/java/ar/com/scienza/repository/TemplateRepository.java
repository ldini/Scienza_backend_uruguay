package ar.com.scienza.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.scienza.entity.Template;


public interface TemplateRepository extends JpaRepository<Template, Integer> {

	/**
	 * Busca todos los templates ordenados alfabeticamente
	 * @param token
	 * @return
	 */
	public List<Template> findAllByFechaDeleteIsNullOrderByTituloAsc();
}
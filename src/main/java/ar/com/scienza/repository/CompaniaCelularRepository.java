package ar.com.scienza.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.scienza.entity.CompaniaCelular;


public interface CompaniaCelularRepository extends JpaRepository<CompaniaCelular, Integer> {
	
	/**
	 * Busca por CODIGO
	 * @param nombre
	 * @return
	 */
	public CompaniaCelular findByCodigo(String codigo);
	

	/**
	 * Busca por DESCRIPCION
	 * @param nombre
	 * @return
	 */
	public CompaniaCelular findByDescripcionIgnoreCase(String descripcion);
}

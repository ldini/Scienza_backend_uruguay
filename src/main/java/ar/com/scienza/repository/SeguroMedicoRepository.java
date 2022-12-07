package ar.com.scienza.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.scienza.entity.SeguroMedico;


public interface SeguroMedicoRepository extends JpaRepository<SeguroMedico, Integer> {
	
	/**
	 * Busca seguro medico por ID SAP
	 * @param sapId
	 * @return
	 */
	public SeguroMedico findBySapId(Long sapId);

	/**
	 * Busca seguro medico por SAP_DESCRIPTION
	 * @param
	 * @return
	 */
	public SeguroMedico findBySapDescripcion(String sapDescription);


	
	
	/**
	 * Busca la lista de seguros medicos
	 * @return
	 */
	public List<SeguroMedico> findAllByFechaDeleteIsNullOrderByDescripcionAsc();
}

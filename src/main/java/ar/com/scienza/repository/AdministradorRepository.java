package ar.com.scienza.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.scienza.entity.Administrador;


public interface AdministradorRepository extends JpaRepository<Administrador, Integer> {
	
	
	/**
	 * Busca un administrador por su usuario
	 * @param usuarioId
	 * @return
	 */
	public Administrador findByUsuarioId(Integer usuarioId);

	
	/**
	 * Busca los administradores operativos
	 * @return
	 */
	public List<Administrador> findAllByOperativoAndFechaDeleteIsNull(Boolean operativo);
}

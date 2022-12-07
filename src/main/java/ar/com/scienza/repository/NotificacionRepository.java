package ar.com.scienza.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.scienza.entity.Notificacion;


public interface NotificacionRepository extends JpaRepository<Notificacion, Integer> {

	/**
	 * Obtiene los ultimos 50 mensajes de notificacion
	 * @param afiliadoId
	 * @return
	 */
	public List<Notificacion> findByAfiliadoIdAndFechaDeleteIsNullOrderByFechaInsertDesc(Integer afiliadoId);
	
	
	/**
	 * Obtiene la cantidad de notificaciones no leidas
	 * @param estado
	 * @return
	 */
	public Long countByAfiliadoIdAndLeidoFalseAndFechaDeleteIsNull(Integer afiliadoId);

	public Integer countByMensajeIdAndFechaDeleteIsNull(Integer mensajeId);
}
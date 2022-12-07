package ar.com.scienza.repository;

import ar.com.scienza.entity.Afiliado;
import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.scienza.entity.Mensaje;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface MensajeRepository extends JpaRepository<Mensaje, Integer> {

    /*
    Obtiene la lista de mensajes que envió un admin manualmente.
     */
    @Query("SELECT m FROM Mensaje m WHERE m.administrador IS NOT NULL AND m.id IN(SELECT n.mensaje.id FROM Notificacion n WHERE n.tipoNotificacion = ?1 AND n.fechaDelete IS NULL)")
    public List<Mensaje> findAllByTipoNotificacion(String tipoNotificacion);
}
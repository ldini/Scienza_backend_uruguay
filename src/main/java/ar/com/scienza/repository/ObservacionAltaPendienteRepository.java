package ar.com.scienza.repository;

import ar.com.scienza.entity.ObservacionAltaPendiente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ObservacionAltaPendienteRepository extends JpaRepository<ObservacionAltaPendiente, Integer> {

    /**
     * Busca las observaciones por id de alta pendiente
     * @param alta_pendiente_id
     * @return
     */
    public List<ObservacionAltaPendiente> findAllByBandejaAltaPendienteId(Integer altaPendienteId);
}

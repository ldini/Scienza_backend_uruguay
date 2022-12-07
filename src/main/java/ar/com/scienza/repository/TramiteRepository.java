package ar.com.scienza.repository;

import ar.com.scienza.entity.Tramite;
import ar.com.scienza.transformer.TramiteTRF;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface TramiteRepository extends JpaRepository<Tramite, Integer> {


    @Query("SELECT new ar.com.scienza.transformer.TramiteTRF(t.id , t.fechaInsert) "
            + " FROM Tramite t"
            + " WHERE t.afiliado.id = ?1 AND t.fechaDelete IS NULL "
            + " ORDER BY t.fechaInsert DESC"

    )
    public List<TramiteTRF> findAllTRFByAfiliadoId(Integer afiliadoId);

    /**
     * Obtiene la cantidad de consultas no leidas
     * @param
     * @return
     */
    @Query("SELECT COUNT(*) FROM Tramite t "
            + " WHERE t.afiliado.id = ?1"
    )
    public Long countByTramite(Integer afiliadoId);

    /**
     * Busca una consulta por su afiliado
     * @param tramiteId
     * @param afiliadoId
     * @return
     */
    public Tramite findOneByIdAndAfiliadoId(Integer tramiteId, Integer afiliadoId);

}

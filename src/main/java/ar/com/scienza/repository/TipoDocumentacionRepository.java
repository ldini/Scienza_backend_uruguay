package ar.com.scienza.repository;

import ar.com.scienza.entity.TipoConsulta;
import ar.com.scienza.entity.TipoDocumentacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TipoDocumentacionRepository  extends JpaRepository<TipoDocumentacion, Integer> {

    /**
     * Busca todos los tipos de consulta habilitados
     * @return
     */
    public List<TipoDocumentacion> findAllByFechaDeleteIsNullOrderByIdAsc();

    public TipoDocumentacion findOneByCodigoAndFechaDeleteIsNullOrderByIdAsc(String codigo);

}

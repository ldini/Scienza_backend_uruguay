package ar.com.scienza.repository;

import ar.com.scienza.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email, Integer> {

    /**
     * Busca los destinatarios por un codigo
     * @param tipoEmail
     * @return
     */
    public Email findByTipoEmail(String tipoEmail);
}

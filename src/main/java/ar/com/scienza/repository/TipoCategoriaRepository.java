package ar.com.scienza.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.scienza.entity.TipoCategoria;


public interface TipoCategoriaRepository extends JpaRepository<TipoCategoria, Integer> {

}
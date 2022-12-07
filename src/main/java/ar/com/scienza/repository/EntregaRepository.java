package ar.com.scienza.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.scienza.entity.Entrega;


public interface EntregaRepository extends JpaRepository<Entrega, Integer> {

}
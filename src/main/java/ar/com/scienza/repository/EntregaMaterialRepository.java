package ar.com.scienza.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.scienza.entity.EntregaMaterial;


public interface EntregaMaterialRepository extends JpaRepository<EntregaMaterial, Integer> {

}
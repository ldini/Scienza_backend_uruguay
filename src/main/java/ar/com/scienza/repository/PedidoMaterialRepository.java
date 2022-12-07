package ar.com.scienza.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.scienza.entity.PedidoMaterial;


public interface PedidoMaterialRepository extends JpaRepository<PedidoMaterial, Integer> {

}
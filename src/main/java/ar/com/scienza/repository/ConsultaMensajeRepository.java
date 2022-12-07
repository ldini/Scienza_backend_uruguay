package ar.com.scienza.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.scienza.entity.ConsultaMensaje;


public interface ConsultaMensajeRepository extends JpaRepository<ConsultaMensaje, Integer> {

}
package ar.com.scienza.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.scienza.entity.Transaction;


public interface TransaccionRepository extends JpaRepository<Transaction, Integer> {
	
}

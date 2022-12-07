package ar.com.scienza.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.scienza.entity.Rol;


public interface RolRepository extends JpaRepository<Rol, Integer> {
	
	public Rol findByNombre(String nombre);
}

package ar.com.scienza.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.scienza.entity.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	
	/**
	 * Busca el usuario con nombre y password
	 * 
	 * @param userName
	 * @param password
	 * @return
	 */
	public Usuario findByUserNameAndPassword(String userName, String password);
}

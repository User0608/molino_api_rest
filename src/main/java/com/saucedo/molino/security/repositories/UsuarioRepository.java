package com.saucedo.molino.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saucedo.molino.security.models.Usuario;

@Repository 
public interface UsuarioRepository extends JpaRepository<Usuario,Long>{
	public Usuario findByUsername(String username);
}

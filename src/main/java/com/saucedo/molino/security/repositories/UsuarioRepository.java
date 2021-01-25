package com.saucedo.molino.security.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.saucedo.molino.security.models.Usuario;

@Repository 
public interface UsuarioRepository extends JpaRepository<Usuario,Long>{
	public Usuario findByUsername(String username);
	@Modifying
    @Transactional
	@Query(value="DELETE FROM usuario_roles WHERE usuario_id=:usuarioId",nativeQuery = true)
	public void deleteAllRelationWithRole(Long usuarioId);
}

package com.saucedo.molino.security.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.saucedo.molino.security.models.Usuario;
import com.saucedo.molino.security.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public Usuario findUsuarioByUsername(String username) {
		return this.usuarioRepository.findByUsername(username);
	}
	public List<Usuario> findAll(){
		return this.usuarioRepository.findAll();
	}
	public void save(Usuario usuario) {
		usuario.setPassword(this.bCryptPasswordEncoder.encode(usuario.getPassword()));
		this.usuarioRepository.save(usuario);	
	}	
}

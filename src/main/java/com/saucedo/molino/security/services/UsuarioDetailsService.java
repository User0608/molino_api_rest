package com.saucedo.molino.security.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.saucedo.molino.security.models.KRole;
import com.saucedo.molino.security.models.Usuario;
@Service
public class UsuarioDetailsService implements UserDetailsService{

	@Autowired
	private UsuarioService usuarioService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = this.usuarioService.findUsuarioByUsername(username);
		if(usuario!=null) {
			List<String> roles = new ArrayList<>();
			for(KRole role : usuario.getRoles()) {
				roles.add(role.getNombre());
			}
			return this.userBuilder(usuario.getUsername(),usuario.getPassword(),usuario.isState(),roles);
		}
		throw new UsernameNotFoundException("Usuario no registrado");
		
	}
	private User userBuilder(String username,String password,boolean state,List<String> roles) {
		boolean enabled = state;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired= true;
		boolean accountNonLocked = true;
		List<GrantedAuthority> authorities = new ArrayList<>();
		for(String role:roles) {
			authorities.add(new SimpleGrantedAuthority("ROLE_"+role));
		}
		return new User(username,password,enabled,accountNonExpired,credentialsNonExpired,accountNonLocked,authorities);
	}
}

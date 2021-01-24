package com.saucedo.molino.security.controllers;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.saucedo.molino.routes.APIUserPath;
import com.saucedo.molino.security.HasRole;
import com.saucedo.molino.security.exceptions.JwtException;
import com.saucedo.molino.security.jwt.JwtService;
import com.saucedo.molino.security.models.KRole;
import com.saucedo.molino.security.models.Usuario;
import com.saucedo.molino.security.repositories.KRoleRepository;
import com.saucedo.molino.security.services.UsuarioService;
import com.saucedo.molino.security.utils.UsuarioJSON;
import com.saucedo.molino_json_models.security.JRole;
import com.saucedo.molino_json_models.security.JUsuario;
import com.saucedo.molino_json_models.security.SessionRequest;
import com.saucedo.molino_json_models.security.SessionResponse;

@RestController()
@RequestMapping(APIUserPath.CONTROLLER_PATH)
public class UsuarioController {
	@Autowired
	private AuthenticationManager autenticationManager;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private KRoleRepository kroleRepository;

	@Autowired
	private JwtService jwtService;

	//@PreAuthorize("authenticated")
	@RequestMapping(value = APIUserPath.AUTHORIZATION, method = RequestMethod.POST)
	public ResponseEntity<?> createAuthentificationToken(@RequestBody SessionRequest authenticationRequest)
			throws IllegalArgumentException, JWTCreationException, UnsupportedEncodingException, JwtException {		
		this.autenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		Usuario usuario = this.usuarioService.findUsuarioByUsername(authenticationRequest.getUsername());	
		String token = this.jwtService.createToken(usuario);		
		return ResponseEntity.ok(new SessionResponse(token,this.jwtService.user(token),this.jwtService.roles(token)));
	}
	@PreAuthorize(HasRole.ADMIN)
	@RequestMapping(value = APIUserPath.GET_USUARI0, method = RequestMethod.GET)
	public ResponseEntity<?> usuarios(){
		List<Usuario> usuarios=this.usuarioService.findAll();
		List<JUsuario> urj = new ArrayList<JUsuario>();
		for(Usuario u:usuarios) {
			urj.add(UsuarioJSON.build(u));
		}
		
		return ResponseEntity.ok(urj);
	}
	@PreAuthorize(HasRole.ADMIN)
	@RequestMapping(value = APIUserPath.INSERT_USUARIO, method = RequestMethod.POST)
	public ResponseEntity<?> registrar(@RequestBody JUsuario usuario){
		List<JRole> roles = usuario.getRoles();
		List<KRole> listOfRoles =this.kroleRepository.findAll();
		Usuario u = new Usuario();
		for(JRole rol:roles) {
			u.addRole(this.filterRole(listOfRoles,rol.getName()));
		}
		u.setUsername(usuario.getUsername());
		u.setPassword(usuario.getPassword());
		u.setOwner(usuario.getOwner());
		u.setState(usuario.getStringOfStatus().equals(JUsuario.STRATUS_ACTIVE)? true : false);
		System.out.println(u.toString());
		return null;
	}
	@PreAuthorize(HasRole.ADMIN)
	@RequestMapping(value = APIUserPath.UPDATE_USUARIO, method = RequestMethod.PUT)
	public ResponseEntity<?> update(@RequestBody JUsuario usuario){
		
		System.out.println(">>>>>> "+usuario.getId());
		//return ResponseEntity.ok(urj);
		System.out.println(">>>>>> Username: "+usuario.getUsername());
		System.out.println(">>>>>> Password: "+usuario.getPassword());
		System.out.println(">>>>>> Owner: "+usuario.getOwner());
		System.out.println(">>>>>> Status: "+usuario.getStringOfStatus());
		System.out.println(">>>>>> Roles: "+usuario.getStringRoles());
		return null;
	}
	
	private KRole filterRole(List<KRole> roles,String roleName) {
		for(KRole rol:roles) {
			if(rol.getNombre().equals(roleName)) return rol;
		}
		return null;
	}
}

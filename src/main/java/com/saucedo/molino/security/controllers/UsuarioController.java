package com.saucedo.molino.security.controllers;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
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
import com.saucedo.molino_json_models.JResponse;
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

	@RequestMapping(value = APIUserPath.AUTHORIZATION, method = RequestMethod.POST)
	public ResponseEntity<?> createAuthentificationToken(@RequestBody SessionRequest authenticationRequest)
			throws IllegalArgumentException, JWTCreationException, UnsupportedEncodingException, JwtException {
		this.autenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		Usuario usuario = this.usuarioService.findUsuarioByUsername(authenticationRequest.getUsername());
		String token = this.jwtService.createToken(usuario);
		return ResponseEntity.ok(new SessionResponse(token, this.jwtService.user(token), this.jwtService.roles(token)));
	}

	@PreAuthorize(HasRole.ADMIN)
	@RequestMapping(value = APIUserPath.GET_USUARI0, method = RequestMethod.GET)
	public ResponseEntity<?> usuarios() {
		List<Usuario> usuarios = this.usuarioService.findAll();
		List<JUsuario> urj = new ArrayList<JUsuario>();
		for (Usuario u : usuarios) {
			urj.add(UsuarioJSON.build(u));
		}
		return ResponseEntity.ok(urj);
	}

	@PreAuthorize(HasRole.ADMIN)
	@RequestMapping(value = APIUserPath.INSERT_USUARIO, method = RequestMethod.POST)
	public ResponseEntity<?> registrar(@RequestBody JUsuario usuario) {
		List<JRole> roles = usuario.getRoles();
		List<KRole> listOfRoles = this.kroleRepository.findAll();
		Usuario u = new Usuario();
		for (JRole rol : roles) {
			u.addRole(this.filterRole(listOfRoles, rol.getName()));
		}
		u.setUsername(usuario.getUsername());
		u.setPassword(usuario.getPassword());
		u.setOwner(usuario.getOwner());
		u.setState(usuario.getStringOfStatus().equals(JUsuario.STRATUS_ACTIVE) ? true : false);
		Usuario oldUsuario = this.usuarioService.findUsuarioByUsername(usuario.getUsername());
		if (oldUsuario == null)
			this.usuarioService.save(u);
		else
			return ResponseEntity.ok(new JResponse(JResponse.ERROR_USUARI_EXISTE));
		return ResponseEntity.ok(new JResponse(JResponse.OK));
	}

	@PreAuthorize(HasRole.ADMIN)
	@RequestMapping(value = APIUserPath.UPDATE_USUARIO, method = RequestMethod.PUT)
	public ResponseEntity<?> update(@RequestBody JUsuario usuario) {
		Usuario oldUsuario = this.usuarioService.findUsuarioByUsername(usuario.getUsername());
		this.usuarioService.deleteAllRelationWithRole(oldUsuario.getId());
		List<JRole> roles = usuario.getRoles();
		List<KRole> listOfRoles = this.kroleRepository.findAll();
		oldUsuario.setRoles(new HashSet<KRole>());
		for (JRole rol : roles) {
			oldUsuario.addRole(this.filterRole(listOfRoles, rol.getName()));
		}
		boolean updatePassword = false;
		if (usuario.getPassword().length() != 0)
			updatePassword = true;

		oldUsuario.setOwner(usuario.getOwner());
		oldUsuario.setState(usuario.getStringOfStatus().equals(JUsuario.STRATUS_ACTIVE) ? true : false);

		if (updatePassword) {
			oldUsuario.setPassword(usuario.getPassword());
			this.usuarioService.save(oldUsuario);
		} else {
			this.usuarioService.saveWithoutEncrypt(oldUsuario);
		}
		return ResponseEntity.ok(new JResponse(JResponse.OK));
	}

	@PreAuthorize(HasRole.ADMIN)
	@RequestMapping(value = APIUserPath.DELETE_USUARIO, method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable(value = "username") String username) {
		Usuario usuario = this.usuarioService.findUsuarioByUsername(username);
		this.usuarioService.deleteAllRelationWithRole(usuario.getId());		
		usuario.setRoles(new HashSet<KRole>());
		this.usuarioService.delete(usuario);
		return ResponseEntity.ok(new JResponse(JResponse.OK));
	}

	private KRole filterRole(List<KRole> roles, String roleName) {
		for (KRole rol : roles) {
			if (rol.getNombre().equals(roleName))
				return rol;
		}
		return null;
	}
}

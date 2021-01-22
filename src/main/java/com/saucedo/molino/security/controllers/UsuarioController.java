package com.saucedo.molino.security.controllers;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.saucedo.molino.routes.APIUserPath;
import com.saucedo.molino.security.AuthenticationRequest;
import com.saucedo.molino.security.AuthenticationResponse;
import com.saucedo.molino.security.exceptions.JwtException;
import com.saucedo.molino.security.jwt.JwtService;
import com.saucedo.molino.security.models.Usuario;
import com.saucedo.molino.security.services.UsuarioService;

@RestController()
@RequestMapping(APIUserPath.CONTROLLER_PATH)
public class UsuarioController {
	@Autowired
	private AuthenticationManager autenticationManager;
	@Autowired
	private UsuarioService usuarioService;
//	@Autowired
//	private KRoleRepository kroleRepository;

	@Autowired
	private JwtService jwtService;

	@PreAuthorize("authenticated")
	@RequestMapping(value = APIUserPath.AUTHORIZATION, method = RequestMethod.POST)
	public ResponseEntity<?> createAuthentificationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws IllegalArgumentException, JWTCreationException, UnsupportedEncodingException, JwtException {		
		this.autenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		Usuario usuario = this.usuarioService.findUsuarioByUsername(authenticationRequest.getUsername());
		String token = this.jwtService.createToken(usuario);		
		return ResponseEntity.ok(new AuthenticationResponse(token,this.jwtService.user(token),this.jwtService.roles(token)));
	}
//	@PreAuthorize("authenticated")
//	@RequestMapping(value = APIUserPath.AUTHORIZATION, method = RequestMethod.POST)
//	public ResponseEntity<?> createAuthentificationToken(@RequestBody AuthenticationRequest authenticationRequest){
//		KRole role = this.kroleRepository.findById(new Long(2)).orElse(null);
//		Usuario user = new Usuario("kevin002","maira002","admin.1",1);
//		user.addRole(role);
//		this.usuarioService.save(user);
//		System.out.println("Complete");
//		return null;
//	}

}

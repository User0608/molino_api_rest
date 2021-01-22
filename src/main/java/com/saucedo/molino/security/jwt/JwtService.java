package com.saucedo.molino.security.jwt;


import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.saucedo.molino.security.exceptions.JwtException;
import com.saucedo.molino.security.models.KRole;
import com.saucedo.molino.security.models.Usuario;

@Service
public class JwtService {
	public static final String ROLES = "roles";
	public static final String USER = "user";
	public static final String OWNER = "owner";
	public static final String ISSUER = "com.saucedo";
	public static final Integer EXPIRES_IN_MILLISECOND = 3600000;
	public static final String SECRET ="sr4mkbbr4wdy45974s94l3squ0wtpf15";

	
	public String createToken(Usuario user) 
			throws IllegalArgumentException, JWTCreationException, UnsupportedEncodingException {
		Set<String> roles = new HashSet<>();
		for(KRole role:user.getRoles()) {
			roles.add(role.getNombre());
		}
		return JWT.create()
				.withIssuer(ISSUER)
				.withIssuedAt(new Date())
				.withNotBefore(new Date())
				.withExpiresAt(new Date(System.currentTimeMillis()+EXPIRES_IN_MILLISECOND))
				.withClaim(USER,user.getUsername())
				.withClaim(OWNER, user.getOwner())
				.withArrayClaim(ROLES,roles.toArray(new String[0]))
				.sign(Algorithm.HMAC256(SECRET));
				
	}
	
	public boolean isBearer(String token) {	
		System.out.println(token);
		return token!=null;
	}
	public String user(String token) throws JwtException {
		return this.verify(token).getClaim(USER).asString();
	}
	private DecodedJWT verify(String token) throws JwtException{
		if (!this.isBearer(token)) {
			throw new JwtException("It is no Berear");
		}
		try {
			return JWT.require(Algorithm.HMAC256(SECRET))
					.withIssuer(ISSUER).build()
					.verify(token);
		} catch(Exception e) {
			throw new JwtException("JWT is wrong. " + e.getMessage());
		}		
	}
	public List<String> roles(String token) throws JwtException{		
		return Arrays.asList(this.verify(token).getClaim(ROLES).asArray(String.class));		
	}
}

package com.saucedo.molino.security.filters;

import java.io.IOException;
import java.util.List;

import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.saucedo.molino.routes.APIUserPath;
import com.saucedo.molino.security.exceptions.JwtException;
import com.saucedo.molino.security.jwt.JwtService;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
	public static final String AUTHORIZATION = "Authorization";
	@Autowired
	private JwtService jwtService;

	public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		String token = request.getHeader(AUTHORIZATION);
		if (jwtService.isBearer(token)) {

			List<SimpleGrantedAuthority> authorities;
			try {
				authorities = this.jwtService.roles(token).stream().map((role) -> new SimpleGrantedAuthority("ROLE_"+role))
						.collect(Collectors.toList());
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						this.jwtService.user(token), null, authorities);
				SecurityContextHolder.getContext().setAuthentication(authentication);
			} catch (JwtException e) {
				return;		
				//Todo
			}

		} else {
			if(request.getRequestURI().compareTo(APIUserPath.CONTROLLER_PATH+APIUserPath.AUTHORIZATION)!=0) {
				return;
			}
		}
		filterChain.doFilter(request, response);
	}
}

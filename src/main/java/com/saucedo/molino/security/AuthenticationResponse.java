package com.saucedo.molino.security;

import java.util.List;


public class AuthenticationResponse {
	private String token;
	private String username;
	private List<String> roles;

	
	
	public AuthenticationResponse() {
	}

	

	public AuthenticationResponse(String token, String username, List<String> roles) {
		this.token = token;
		this.username = username;
		this.roles = roles;
	}



	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	
	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public List<String> getRoles() {
		return roles;
	}



	public void setRoles(List<String> roles) {
		this.roles = roles;
	}



	@Override
	public String toString() {
		return "AuthenticationResponse [token=" + token + "]";
	}
	
	
}

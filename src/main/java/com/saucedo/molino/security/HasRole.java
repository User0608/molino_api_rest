package com.saucedo.molino.security;

public class HasRole {
	public static final String ADMIN = "hasRole('ADMIN')";
	public static final String SECADO= "hasAnyRole('SECADO','ADMIN')";
	public static final String RECEPCION = "hasAnyRole('RECEPCION','ADMIN')";


}

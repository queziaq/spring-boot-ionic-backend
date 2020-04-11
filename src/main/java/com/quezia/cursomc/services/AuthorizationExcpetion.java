package com.quezia.cursomc.services;

public class AuthorizationExcpetion extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public AuthorizationExcpetion(String msg) {
		super(msg);
	}
	
	public AuthorizationExcpetion(String msg, Throwable cause) {
		super(msg, cause);
	}

}

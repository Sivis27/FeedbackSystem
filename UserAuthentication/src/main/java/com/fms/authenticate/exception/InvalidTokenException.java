package com.fms.authenticate.exception;


public class InvalidTokenException extends ObjectNotFoundException {
   
	private static final long serialVersionUID = 1L;

	public InvalidTokenException(String token) {
        super(String.format("Invalid token: %s", token)+" in data base ");
    }
}

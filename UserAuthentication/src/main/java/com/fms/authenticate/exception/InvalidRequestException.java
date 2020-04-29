package com.fms.authenticate.exception;

public class InvalidRequestException extends ObjectNotFoundException {

	private static final long serialVersionUID = 1L;

	public InvalidRequestException() {
		super("Invalid Request is found.");
	}
}

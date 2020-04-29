package com.fms.events.exception;

public class InvalidRequestException extends ObjectNotFoundException{

	public InvalidRequestException() {
		super("Request  body not found or invalid ");
		// TODO Auto-generated constructor stub
	}

}

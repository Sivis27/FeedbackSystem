package com.fms.events.exception;

public class InvalidIdException extends ObjectNotFoundException{

	public InvalidIdException() {
		super(" Invalid Id found in Database / Id unavailable ");
		// TODO Auto-generated constructor stub
	}

}

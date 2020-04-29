package com.fms.feedback.exception;

public class InvalidIdException extends ObjectNotFoundException{

	public InvalidIdException() {
		super(" Invalid feedbackType found in Database / Id unavailable ");
		// TODO Auto-generated constructor stub
	}

}

package com.fms.events.exception;
public abstract class ObjectNotFoundException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ObjectNotFoundException(String message) {
        super(message);
    }
}

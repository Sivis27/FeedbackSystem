package com.fms.email.exception;

public class FMSEmailServiceException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public FMSEmailServiceException() {
	}

	public FMSEmailServiceException(String arg0) {
		super(arg0);
	}

	public FMSEmailServiceException(Throwable arg0) {
		super(arg0);
	}

	public FMSEmailServiceException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public FMSEmailServiceException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}

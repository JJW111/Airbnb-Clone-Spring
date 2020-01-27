package com.clone.airbnb.exception;

public class FailedToSendMailException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	
	

	
	public FailedToSendMailException(Throwable cause) {
		super(cause);
	}

	
	
	public FailedToSendMailException(String message) {
		super(message);
	}

	
	
	public FailedToSendMailException(String message, Throwable cause) {
		super(message, cause);
	}
	
}

package com.clone.airbnb.exception;

public class InvalidFormTypeException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	
	

	
	public InvalidFormTypeException(Throwable cause) {
		super(cause);
	}

	
	
	public InvalidFormTypeException(String message) {
		super(message);
	}

	
	
	public InvalidFormTypeException(String message, Throwable cause) {
		super(message, cause);
	}
	
}

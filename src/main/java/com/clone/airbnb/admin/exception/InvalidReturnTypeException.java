package com.clone.airbnb.admin.exception;

public class InvalidReturnTypeException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	
	

	
	public InvalidReturnTypeException(Throwable cause) {
		super(cause);
	}

	
	
	public InvalidReturnTypeException(String message) {
		super(message);
	}

	
	
	public InvalidReturnTypeException(String message, Throwable cause) {
		super(message, cause);
	}
	
}

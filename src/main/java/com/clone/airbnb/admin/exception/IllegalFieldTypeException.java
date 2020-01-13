package com.clone.airbnb.admin.exception;

public class IllegalFieldTypeException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	
	

	
	public IllegalFieldTypeException(Throwable cause) {
		super(cause);
	}

	
	
	public IllegalFieldTypeException(String message) {
		super(message);
	}

	
	
	public IllegalFieldTypeException(String message, Throwable cause) {
		super(message, cause);
	}
	
}

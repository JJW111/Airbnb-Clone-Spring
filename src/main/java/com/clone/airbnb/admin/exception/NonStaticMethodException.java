package com.clone.airbnb.admin.exception;

public class NonStaticMethodException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	
	

	
	public NonStaticMethodException(Throwable cause) {
		super(cause);
	}

	
	
	public NonStaticMethodException(String message) {
		super(message);
	}

	
	
	public NonStaticMethodException(String message, Throwable cause) {
		super(message, cause);
	}
	
}

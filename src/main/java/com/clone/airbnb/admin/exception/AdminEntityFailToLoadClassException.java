package com.clone.airbnb.admin.exception;

public class AdminEntityFailToLoadClassException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	
	

	
	public AdminEntityFailToLoadClassException(Throwable cause) {
		super(cause);
	}

	
	
	public AdminEntityFailToLoadClassException(String message) {
		super(message);
	}

	
	
	public AdminEntityFailToLoadClassException(String message, Throwable cause) {
		super(message, cause);
	}
	
}

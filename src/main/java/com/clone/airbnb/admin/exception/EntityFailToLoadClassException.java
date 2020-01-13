package com.clone.airbnb.admin.exception;

public class EntityFailToLoadClassException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	
	

	
	public EntityFailToLoadClassException(Throwable cause) {
		super(cause);
	}

	
	
	public EntityFailToLoadClassException(String message) {
		super(message);
	}

	
	
	public EntityFailToLoadClassException(String message, Throwable cause) {
		super(message, cause);
	}
	
}

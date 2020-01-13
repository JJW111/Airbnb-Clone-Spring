package com.clone.airbnb.admin.exception;

public class EntitySchemaInvalidEntityClassException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	
	

	
	public EntitySchemaInvalidEntityClassException(Throwable cause) {
		super(cause);
	}

	
	
	public EntitySchemaInvalidEntityClassException(String message) {
		super(message);
	}

	
	
	public EntitySchemaInvalidEntityClassException(String message, Throwable cause) {
		super(message, cause);
	}
	
}

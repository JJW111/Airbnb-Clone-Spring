package com.clone.airbnb.admin.exception;

public class EntitySchemaCreationException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	
	

	
	public EntitySchemaCreationException(Throwable cause) {
		super(cause);
	}

	
	
	public EntitySchemaCreationException(String message) {
		super(message);
	}

	
	
	public EntitySchemaCreationException(String message, Throwable cause) {
		super(message, cause);
	}
	
}

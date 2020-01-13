package com.clone.airbnb.admin.exception;

public class ApplicationContextBeanCreationException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	
	

	
	public ApplicationContextBeanCreationException(Throwable cause) {
		super(cause);
	}

	
	
	public ApplicationContextBeanCreationException(String message) {
		super(message);
	}

	
	
	public ApplicationContextBeanCreationException(String message, Throwable cause) {
		super(message, cause);
	}
	
}

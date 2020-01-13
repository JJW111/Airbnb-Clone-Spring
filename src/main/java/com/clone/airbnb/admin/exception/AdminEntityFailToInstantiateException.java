package com.clone.airbnb.admin.exception;

public class AdminEntityFailToInstantiateException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	
	

	
	public AdminEntityFailToInstantiateException(Throwable cause) {
		super(cause);
	}

	
	
	public AdminEntityFailToInstantiateException(String message) {
		super(message);
	}

	
	
	public AdminEntityFailToInstantiateException(String message, Throwable cause) {
		super(message, cause);
	}
	
}

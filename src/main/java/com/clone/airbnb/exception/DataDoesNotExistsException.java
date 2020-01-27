package com.clone.airbnb.exception;

public class DataDoesNotExistsException extends GithubException {
	
	private static final long serialVersionUID = 1L;

	
	

	
	public DataDoesNotExistsException(Throwable cause) {
		super(cause);
	}

	
	
	public DataDoesNotExistsException(String message) {
		super(message);
	}

	
	
	public DataDoesNotExistsException(String message, Throwable cause) {
		super(message, cause);
	}
	
}

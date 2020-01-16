package com.clone.airbnb.exception;

public class UserDoesNotExistsException extends GithubException {
	
	private static final long serialVersionUID = 1L;

	
	

	
	public UserDoesNotExistsException(Throwable cause) {
		super(cause);
	}

	
	
	public UserDoesNotExistsException(String message) {
		super(message);
	}

	
	
	public UserDoesNotExistsException(String message, Throwable cause) {
		super(message, cause);
	}
	
}

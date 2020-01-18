package com.clone.airbnb.exception;

public class AlreadyVerifiedUserException extends GithubException {
	
	private static final long serialVersionUID = 1L;

	
	

	
	public AlreadyVerifiedUserException(Throwable cause) {
		super(cause);
	}

	
	
	public AlreadyVerifiedUserException(String message) {
		super(message);
	}

	
	
	public AlreadyVerifiedUserException(String message, Throwable cause) {
		super(message, cause);
	}
	
}

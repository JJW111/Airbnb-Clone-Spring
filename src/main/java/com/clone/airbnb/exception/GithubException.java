package com.clone.airbnb.exception;

public class GithubException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	
	

	
	public GithubException(Throwable cause) {
		super(cause);
	}

	
	
	public GithubException(String message) {
		super(message);
	}

	
	
	public GithubException(String message, Throwable cause) {
		super(message, cause);
	}
	
}

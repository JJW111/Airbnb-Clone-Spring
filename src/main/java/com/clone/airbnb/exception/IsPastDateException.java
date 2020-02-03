package com.clone.airbnb.exception;

public class IsPastDateException extends GithubException {
	
	private static final long serialVersionUID = 1L;

	
	

	
	public IsPastDateException(Throwable cause) {
		super(cause);
	}

	
	
	public IsPastDateException(String message) {
		super(message);
	}

	
	
	public IsPastDateException(String message, Throwable cause) {
		super(message, cause);
	}
	
}

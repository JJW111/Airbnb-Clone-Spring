package com.clone.airbnb.exception;

public class GithubPrivateEmailException extends GithubException {
	
	private static final long serialVersionUID = 1L;

	
	

	
	public GithubPrivateEmailException(Throwable cause) {
		super(cause);
	}

	
	
	public GithubPrivateEmailException(String message) {
		super(message);
	}

	
	
	public GithubPrivateEmailException(String message, Throwable cause) {
		super(message, cause);
	}
	
}

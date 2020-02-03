package com.clone.airbnb.exception;

public class AlreadyReservedException extends GithubException {
	
	private static final long serialVersionUID = 1L;

	
	

	
	public AlreadyReservedException(Throwable cause) {
		super(cause);
	}

	
	
	public AlreadyReservedException(String message) {
		super(message);
	}

	
	
	public AlreadyReservedException(String message, Throwable cause) {
		super(message, cause);
	}
	
}

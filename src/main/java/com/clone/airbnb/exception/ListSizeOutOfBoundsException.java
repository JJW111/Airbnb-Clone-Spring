package com.clone.airbnb.exception;

public class ListSizeOutOfBoundsException extends GithubException {
	
	private static final long serialVersionUID = 1L;

	
	

	
	public ListSizeOutOfBoundsException(Throwable cause) {
		super(cause);
	}

	
	
	public ListSizeOutOfBoundsException(String message) {
		super(message);
	}

	
	
	public ListSizeOutOfBoundsException(String message, Throwable cause) {
		super(message, cause);
	}
	
}

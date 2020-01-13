package com.clone.airbnb.exception;

public class KakaoEmailDoesNotExistException extends GithubException {
	
	private static final long serialVersionUID = 1L;

	
	

	
	public KakaoEmailDoesNotExistException(Throwable cause) {
		super(cause);
	}

	
	
	public KakaoEmailDoesNotExistException(String message) {
		super(message);
	}

	
	
	public KakaoEmailDoesNotExistException(String message, Throwable cause) {
		super(message, cause);
	}
	
}

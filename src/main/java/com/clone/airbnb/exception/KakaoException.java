package com.clone.airbnb.exception;

public class KakaoException extends GithubException {
	
	private static final long serialVersionUID = 1L;

	
	

	
	public KakaoException(Throwable cause) {
		super(cause);
	}

	
	
	public KakaoException(String message) {
		super(message);
	}

	
	
	public KakaoException(String message, Throwable cause) {
		super(message, cause);
	}
	
}

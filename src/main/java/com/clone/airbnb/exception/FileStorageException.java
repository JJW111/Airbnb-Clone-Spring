package com.clone.airbnb.exception;

public class FileStorageException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	
	

	
	public FileStorageException(Throwable cause) {
		super(cause);
	}

	
	
	public FileStorageException(String message) {
		super(message);
	}

	
	
	public FileStorageException(String message, Throwable cause) {
		super(message, cause);
	}
	
}

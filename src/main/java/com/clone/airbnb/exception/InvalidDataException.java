package com.clone.airbnb.exception;

import org.springframework.validation.Errors;

public class InvalidDataException extends RuntimeException {
	
	private static final long serialVersionUID = 942662542314859586L;
	
	
	
	private Errors errors;
	
	
	
    public InvalidDataException(String msg, Errors errors) {
        super(msg);
        setErrors(errors);
    }

    
    
    public Errors getErrors() {
        return errors;
    }

    
    
    public void setErrors(Errors errors) {
        this.errors = errors;
    }
}

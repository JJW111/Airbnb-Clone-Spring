package com.clone.airbnb.utils;

import javax.validation.Validation;

import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

import com.clone.airbnb.exception.InvalidDataException;

import lombok.Getter;

public class ValidatorUtils {
	@Getter
	private static final Validator validator = new SpringValidatorAdapter(Validation.buildDefaultValidatorFactory().getValidator());
	
	public static Object validate(Object entry) {
		Errors errors = new BeanPropertyBindingResult(entry, entry.getClass().getName());
		
		validator.validate(entry, errors);
		
		if (errors == null || errors.getAllErrors().isEmpty()) {
			return entry;
		} else {
			throw new InvalidDataException(errors.getAllErrors().toString(), errors);
		}
	}
}

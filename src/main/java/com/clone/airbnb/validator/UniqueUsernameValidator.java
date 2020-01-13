package com.clone.airbnb.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.clone.airbnb.repository.UserRepository;
import com.clone.airbnb.utils.BeanUtils;
import com.clone.airbnb.validator.annotation.UniqueUsername;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {
	
	private UserRepository userRepository;

	@Override
	public void initialize(UniqueUsername constraintAnnotation) {
		this.userRepository = (UserRepository) BeanUtils.getBean(UserRepository.class); 
	}
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return value != null && !userRepository.existsByUsername(value);
	}
	
}

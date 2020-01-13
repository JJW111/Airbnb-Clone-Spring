package com.clone.airbnb.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

import com.clone.airbnb.dto.SafeUser;
import com.clone.airbnb.repository.UserRepository;
import com.clone.airbnb.utils.BeanUtils;

public class SafeUserByUsernameFormatter implements Formatter<SafeUser>{

	@Override
	public String print(SafeUser object, Locale locale) {
		return object.toString();
	}

	@Override
	public SafeUser parse(String text, Locale locale) throws ParseException {
		return ((UserRepository) BeanUtils.getBean(UserRepository.class)).findByUsername(text, SafeUser.class);
	}
	
}

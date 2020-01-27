package com.clone.airbnb.formatter;

import java.text.ParseException;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import com.clone.airbnb.entity.User;
import com.clone.airbnb.repository.UserRepository;

@Component
public class UserFormatter implements Formatter<User> {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public String print(User object, Locale locale) {
		return object.toString();
	}

	@Override
	public User parse(String text, Locale locale) throws ParseException {
		if (text == null) return null;
		
		Optional<User> opt = userRepository.findById(Integer.parseInt(text));
		
		if (opt.isPresent()) {
			return opt.get();
		} else {
			return null;
		}
	}
	
}

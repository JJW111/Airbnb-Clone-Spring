package com.clone.airbnb.formatter;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import com.clone.airbnb.entity.User;
import com.clone.airbnb.repository.UserRepository;

@Component
public class UsersFormatter implements Formatter<List<User>>{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public String print(List<User> object, Locale locale) {
		return object.toString();
	}

	@Override
	public List<User> parse(String text, Locale locale) throws ParseException {
		if (text == null) return null;
		
		List<User> users = null;
		
		if (!text.trim().isEmpty()) {
			users = new ArrayList<>();
			for (String s : text.split(",")) {
				Optional<User> opt = userRepository.findById(Integer.parseInt(s));
				
				if (opt.isPresent()) {
					users.add(opt.get());
				}
			}
		}
		
		if (users != null && !users.isEmpty()) {
			return users;
		} else {
			return null;
		}
	}
	
}

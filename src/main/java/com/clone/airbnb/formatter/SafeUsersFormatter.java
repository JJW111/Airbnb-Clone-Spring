package com.clone.airbnb.formatter;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import org.springframework.format.Formatter;

import com.clone.airbnb.dto.SafeUser;
import com.clone.airbnb.repository.UserRepository;
import com.clone.airbnb.utils.BeanUtils;

public class SafeUsersFormatter implements Formatter<Set<SafeUser>>{

	@Override
	public String print(Set<SafeUser> object, Locale locale) {
		return object.toString();
	}

	@Override
	public Set<SafeUser> parse(String text, Locale locale) throws ParseException {
		Set<SafeUser> users = null;
		
		if (!text.trim().isEmpty()) {
			users = new HashSet<>();
			
			for (String s : text.split(",")) {
				Integer id = Integer.parseInt(s);
				SafeUser safeUser = ((UserRepository) BeanUtils.getBean(UserRepository.class)).findById(id, SafeUser.class);
				if (safeUser == null) return null;
				users.add(safeUser);
			}
		}
		
		return users; 
	}
	
}

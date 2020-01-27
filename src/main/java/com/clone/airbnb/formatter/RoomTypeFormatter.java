package com.clone.airbnb.formatter;

import java.text.ParseException;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import com.clone.airbnb.entity.RoomType;
import com.clone.airbnb.repository.RoomTypeRepository;

@Component
public class RoomTypeFormatter implements Formatter<RoomType>{

	@Autowired
	private RoomTypeRepository roomTypeRepository;
	
	@Override
	public String print(RoomType object, Locale locale) {
		return object.toString();
	}

	@Override
	public RoomType parse(String text, Locale locale) throws ParseException {
		if (text == null) return null;
		
		Optional<RoomType> opt = roomTypeRepository.findById(Integer.valueOf(text));
		
		if (opt.isPresent()) {
			return opt.get();
		} else {
			return null;
		}
	}
	
}

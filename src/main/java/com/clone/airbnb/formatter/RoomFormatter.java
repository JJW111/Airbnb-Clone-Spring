package com.clone.airbnb.formatter;

import java.text.ParseException;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import com.clone.airbnb.entity.Room;
import com.clone.airbnb.repository.RoomRepository;

@Component
public class RoomFormatter implements Formatter<Room>{

	@Autowired
	private RoomRepository roomRepository;
	
	@Override
	public String print(Room object, Locale locale) {
		return object.toString();
	}

	@Override
	public Room parse(String text, Locale locale) throws ParseException {
		if (text == null) return null;
		
		Optional<Room> opt = roomRepository.findById(Integer.parseInt(text));
		
		if (opt.isPresent()) {
			return opt.get();
		} else {
			return null;
		}
	}
	
}

package com.clone.airbnb.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

import com.clone.airbnb.entity.Room;
import com.clone.airbnb.repository.RoomRepository;
import com.clone.airbnb.utils.BeanUtils;

public class RoomFormatter implements Formatter<Room>{

	@Override
	public String print(Room object, Locale locale) {
		return object.toString();
	}

	@Override
	public Room parse(String text, Locale locale) throws ParseException {
		return ((RoomRepository) BeanUtils.getBean(RoomRepository.class)).findById(Integer.parseInt(text)).get();
	}
	
}

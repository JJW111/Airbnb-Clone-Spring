package com.clone.airbnb.formatter;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import org.springframework.format.Formatter;

import com.clone.airbnb.entity.Room;
import com.clone.airbnb.repository.RoomRepository;
import com.clone.airbnb.utils.BeanUtils;

public class RoomsFormatter implements Formatter<Set<Room>>{

	@Override
	public String print(Set<Room> object, Locale locale) {
		return object.toString();
	}

	@Override
	public Set<Room> parse(String text, Locale locale) throws ParseException {
		Set<Room> rooms = null;
		
		if (!text.trim().isEmpty()) {
			rooms = new HashSet<>();
			
			for (String s : text.split(",")) {
				Integer id = Integer.valueOf(s);
				Room room = ((RoomRepository) BeanUtils.getBean(RoomRepository.class)).findById(id).get();
				if (room == null) return null;
				rooms.add(room);
			}
		}
		
		return rooms; 
	}
	
}

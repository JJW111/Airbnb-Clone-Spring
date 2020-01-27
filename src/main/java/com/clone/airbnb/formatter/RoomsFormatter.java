package com.clone.airbnb.formatter;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import com.clone.airbnb.entity.Room;
import com.clone.airbnb.repository.RoomRepository;

@Component
public class RoomsFormatter implements Formatter<List<Room>>{

	@Autowired
	private RoomRepository roomRepository;
	
	@Override
	public String print(List<Room> object, Locale locale) {
		return object.toString();
	}

	@Override
	public List<Room> parse(String text, Locale locale) throws ParseException {
		if (text == null) return null;
		
		List<Room> rooms = null;
		
		if (!text.trim().isEmpty()) {
			rooms = new ArrayList<>();
			
			for (String s : text.split(",")) {
				Integer id = Integer.valueOf(s);
				
				Optional<Room> opt = roomRepository.findById(id);
				
				if (opt.isPresent()) {
					rooms.add(opt.get());
				}
			}
		}
		
		if (rooms != null && !rooms.isEmpty()) {
			return rooms; 
		} else {
			return null;
		}
	}
	
}

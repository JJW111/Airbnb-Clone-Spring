package com.clone.airbnb.formatter;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import com.clone.airbnb.entity.Amenity;
import com.clone.airbnb.repository.AmenityRepository;

@Component
public class AmenitiesFormatter implements Formatter<List<Amenity>>{

	@Autowired
	private AmenityRepository amenityRepository;
	
	@Override
	public String print(List<Amenity> object, Locale locale) {
		return object.toString();
	}

	@Override
	public List<Amenity> parse(String text, Locale locale) throws ParseException {
		if (text == null) return null;
		
		List<Amenity> amenities = null;

		if (!text.trim().isEmpty()) {
			amenities = new ArrayList<>();
			
			for (String s : text.split(",")) {
				Integer id = Integer.valueOf(s);
				
				Optional<Amenity> opt = amenityRepository.findById(id);
				
				if (opt.isPresent()) {
					amenities.add(opt.get());
				}
			}
		}
		
		if (amenities != null && !amenities.isEmpty()) {
			return amenities; 
		} else {
			return null;
		}
	}
	
}

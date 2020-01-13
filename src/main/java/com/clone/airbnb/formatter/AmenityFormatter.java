package com.clone.airbnb.formatter;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import org.springframework.format.Formatter;

import com.clone.airbnb.entity.Amenity;
import com.clone.airbnb.repository.AmenityRepository;
import com.clone.airbnb.utils.BeanUtils;

public class AmenityFormatter implements Formatter<Set<Amenity>>{

	@Override
	public String print(Set<Amenity> object, Locale locale) {
		return object.toString();
	}

	@Override
	public Set<Amenity> parse(String text, Locale locale) throws ParseException {
		Set<Amenity> amenities = null;

		if (!text.trim().isEmpty()) {
			amenities = new HashSet<>();
			
			for (String s : text.split(",")) {
				Integer id = Integer.valueOf(s);
				Amenity amenity = ((AmenityRepository) BeanUtils.getBean(AmenityRepository.class)).findById(id).get();
				if (amenity == null) return null;
				amenities.add(amenity);
			}
		}
		
		return amenities; 
	}
	
}

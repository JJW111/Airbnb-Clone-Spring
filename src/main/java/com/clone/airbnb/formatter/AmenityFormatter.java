package com.clone.airbnb.formatter;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.springframework.format.Formatter;

import com.clone.airbnb.entity.Amenity;
import com.clone.airbnb.repository.AmenityRepository;
import com.clone.airbnb.utils.BeanUtils;

public class AmenityFormatter implements Formatter<List<Amenity>>{

	@Override
	public String print(List<Amenity> object, Locale locale) {
		return object.toString();
	}

	@Override
	public List<Amenity> parse(String text, Locale locale) throws ParseException {
		List<Amenity> amenities = null;

		if (!text.trim().isEmpty()) {
			amenities = new ArrayList<>();
			
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

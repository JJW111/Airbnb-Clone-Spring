package com.clone.airbnb.formatter;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import org.springframework.format.Formatter;

import com.clone.airbnb.entity.Facility;
import com.clone.airbnb.repository.FacilityRepository;
import com.clone.airbnb.utils.BeanUtils;

public class FacilityFormatter implements Formatter<Set<Facility>>{

	@Override
	public String print(Set<Facility> object, Locale locale) {
		return object.toString();
	}

	@Override
	public Set<Facility> parse(String text, Locale locale) throws ParseException {
		Set<Facility> facilities = null;
		
		if (!text.trim().isEmpty()) {
			facilities = new HashSet<>();
			
			for (String s : text.split(",")) {
				Integer id = Integer.valueOf(s);
				Facility facility = ((FacilityRepository) BeanUtils.getBean(FacilityRepository.class)).findById(id).get();
				if (facility == null) return null;
				facilities.add(facility);
			}
		}
		
		return facilities; 
	}
	
}

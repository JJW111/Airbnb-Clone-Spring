package com.clone.airbnb.formatter;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.springframework.format.Formatter;

import com.clone.airbnb.entity.Facility;
import com.clone.airbnb.repository.FacilityRepository;
import com.clone.airbnb.utils.BeanUtils;

public class FacilityFormatter implements Formatter<List<Facility>>{

	@Override
	public String print(List<Facility> object, Locale locale) {
		return object.toString();
	}

	@Override
	public List<Facility> parse(String text, Locale locale) throws ParseException {
		List<Facility> facilities = null;
		
		if (!text.trim().isEmpty()) {
			facilities = new ArrayList<>();
			
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

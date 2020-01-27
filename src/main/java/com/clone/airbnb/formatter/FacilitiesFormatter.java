package com.clone.airbnb.formatter;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import com.clone.airbnb.entity.Facility;
import com.clone.airbnb.repository.FacilityRepository;

@Component
public class FacilitiesFormatter implements Formatter<List<Facility>>{

	@Autowired
	private FacilityRepository facilityRepository;
	
	@Override
	public String print(List<Facility> object, Locale locale) {
		return object.toString();
	}

	@Override
	public List<Facility> parse(String text, Locale locale) throws ParseException {
		if (text == null) return null;
		
		List<Facility> facilities = null;
		
		if (!text.trim().isEmpty()) {
			facilities = new ArrayList<>();
			
			for (String s : text.split(",")) {
				Integer id = Integer.valueOf(s);
				
				Optional<Facility> opt = facilityRepository.findById(id);
				
				if (opt.isPresent()) {
					facilities.add(opt.get());
				}
			}
		}
		
		if (facilities != null && !facilities.isEmpty()) {
			return facilities; 
		} else {
			return null;
		}
	}
	
}

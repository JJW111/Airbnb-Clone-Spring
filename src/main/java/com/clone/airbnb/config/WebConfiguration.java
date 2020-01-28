package com.clone.airbnb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.clone.airbnb.formatter.AmenitiesFormatter;
import com.clone.airbnb.formatter.DateFormatter;
import com.clone.airbnb.formatter.FacilitiesFormatter;
import com.clone.airbnb.formatter.HouseRulesFormatter;
import com.clone.airbnb.formatter.RoomTypeFormatter;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {
	
	@Autowired
	private DateFormatter dateFormatter;
	
	@Autowired
	private RoomTypeFormatter roomTypeFormatter;
	
	@Autowired
	private AmenitiesFormatter amenitiesFormatter;
	
	@Autowired
	private FacilitiesFormatter facilitiesFormatter;
	
	@Autowired
	private HouseRulesFormatter houseRulesFormatter;
	
	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addFormatter(dateFormatter);
		registry.addFormatter(roomTypeFormatter);
		registry.addFormatter(amenitiesFormatter);
		registry.addFormatter(facilitiesFormatter);
		registry.addFormatter(houseRulesFormatter);
	}
	
}
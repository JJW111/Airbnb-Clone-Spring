package com.clone.airbnb.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

import com.clone.airbnb.entity.RoomType;
import com.clone.airbnb.repository.RoomTypeRepository;
import com.clone.airbnb.utils.BeanUtils;

public class RoomTypeFormatter implements Formatter<RoomType>{

	@Override
	public String print(RoomType object, Locale locale) {
		return object.toString();
	}

	@Override
	public RoomType parse(String text, Locale locale) throws ParseException {
		return ((RoomTypeRepository) BeanUtils.getBean(RoomTypeRepository.class)).findById(Integer.valueOf(text)).get();
	}
	
}

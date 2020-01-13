package com.clone.airbnb.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

import com.clone.airbnb.utils.CommonUtils;

public class NullFormatter implements Formatter<String>{

	@Override
	public String print(String object, Locale locale) {
		return object;
	}

	@Override
	public String parse(String text, Locale locale) throws ParseException {
		return CommonUtils.emptyCheck(text);
	}
	
	
}

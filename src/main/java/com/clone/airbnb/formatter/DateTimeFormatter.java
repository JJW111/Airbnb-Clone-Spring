package com.clone.airbnb.formatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import com.clone.airbnb.common.Common;

@Component
public class DateTimeFormatter implements Formatter<Date> {

	@Override
	public String print(Date object, Locale locale) {
		return object.toString();
	}

	@Override
	public Date parse(String text, Locale locale) throws ParseException {
		if (text == null) return null;
		
		return new SimpleDateFormat(Common.DATETIME_FORMAT).parse(text);
	}
	
}

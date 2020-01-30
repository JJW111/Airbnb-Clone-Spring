package com.clone.airbnb.calendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CalendarArray {

	private final static Calendar cal = Calendar.getInstance();
	

	
	public static String[] getDefaultHeader() {
		return new String[] {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
	}
	
	
	
	public static List<Integer> getCalendarDate(int year, int month) {
		List<Integer> calDate = new ArrayList<>();
		
		if (month < 1 || month > 12) {
			throw new IllegalArgumentException("Invalid month[" + month + "]");
		}
		
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DATE, 1);
		
		int startDay = cal.get(Calendar.DAY_OF_WEEK);
		int lastDay = cal.getActualMaximum(Calendar.DATE);
		
		int day = 1;
		
		for (int i = 1; day <= lastDay; i++) {
			
			if (i < startDay) calDate.add(0);
			else {
				calDate.add(day);
				day++;
			}
		}
		
		return calDate;
	}
}

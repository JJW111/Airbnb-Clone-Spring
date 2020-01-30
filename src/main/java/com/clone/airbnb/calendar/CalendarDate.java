package com.clone.airbnb.calendar;

import java.util.Calendar;

public class CalendarDate {

	private final static Calendar cal = Calendar.getInstance();
	
	
	
	public static int getCurrentYear() {
		return cal.get(Calendar.YEAR);
	}
	
	public static int getCurrentMonth() {
		return cal.get(Calendar.MONTH) + 1;
	}
	
	public static int getNextYear() {
		int currentMonth = getCurrentMonth();
		
		if (currentMonth == 12) {
			return getCurrentYear() + 1;
		} else {
			return getCurrentYear();
		}
	}
	
	public static int getNextMonth() {
		int currentMonth = getCurrentMonth();
		
		if (currentMonth == 12) {
			return 1;
		} else {
			return getCurrentMonth() + 1;
		}
	}
	
}

package com.clone.airbnb.calendar;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import lombok.Getter;

@Getter
public class CalendarObject {

	private static final DateFormatSymbols dfs = new DateFormatSymbols(new Locale("en"));
	private final int year;
	private final int month;
	private final List<Integer> dates;
	private final String[] header;
	
	
	
	public CalendarObject(int year, int month, String[] header) {
		this.year = year;
		this.month = month;
		this.dates = CalendarArray.getCalendarDate(year, month);
		this.header = header;
	}
	
	
	public boolean isPast(int year, int month, int date) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, date);
		return isPast(cal);
	}
	
	
	
	public static boolean isPast(Calendar cal) {
		Calendar currentCal = Calendar.getInstance();
		currentCal.set(CalendarDate.getCurrentYear(), CalendarDate.getCurrentMonth() - 1, currentCal.get(Calendar.DAY_OF_MONTH));
		int result = currentCal.compareTo(cal);
		
		if (result > 0) {
			return true;
		} else if (result < 0) {
			return false;
		} else {
			return true;
		}
	}
	
	
	
	public String getMonthSymbol() {
		return dfs.getShortMonths()[month - 1];
	}
	
}

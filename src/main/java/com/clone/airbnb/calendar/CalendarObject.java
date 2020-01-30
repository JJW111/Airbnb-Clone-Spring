package com.clone.airbnb.calendar;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import lombok.Getter;

@Getter
public class CalendarObject {

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
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.set(CalendarDate.getCurrentYear(), CalendarDate.getCurrentMonth() - 1, cal1.get(Calendar.DAY_OF_MONTH));
		cal2.set(year, month - 1, date);
		int result = cal1.compareTo(cal2);

		if (result > 0) {
			return true;
		} else if (result < 0) {
			return false;
		} else {
			return true;
		}
	}
	
}

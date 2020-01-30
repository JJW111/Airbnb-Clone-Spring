package com.clone.airbnb.calendar;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class RoomDetailCalendar {

	private final List<CalendarObject> calendars = new ArrayList<>();
	
	public RoomDetailCalendar() {
		calendars.add(
					new CalendarObject(
							CalendarDate.getCurrentYear(), 
							CalendarDate.getCurrentMonth(),
							CalendarArray.getDefaultHeader())
				);
		calendars.add(
					new CalendarObject(
							CalendarDate.getNextYear(),
							CalendarDate.getNextMonth(),
							CalendarArray.getDefaultHeader())
				);
	}
	
}

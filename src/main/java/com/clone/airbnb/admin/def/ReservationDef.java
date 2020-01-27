package com.clone.airbnb.admin.def;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebRequestDataBinder;

import com.clone.airbnb.admin.annotation.AdminEntityDefinition;
import com.clone.airbnb.admin.schema.AdminEntityConfiguration;
import com.clone.airbnb.admin.schema.vo.FieldList;
import com.clone.airbnb.admin.schema.vo.FieldSet;
import com.clone.airbnb.entity.Reservation;
import com.clone.airbnb.formatter.DateTimeFormatter;
import com.clone.airbnb.formatter.NullFormatter;
import com.clone.airbnb.formatter.RoomFormatter;
import com.clone.airbnb.formatter.UserFormatter;
import com.clone.airbnb.utils.ValidatorUtils;

@Component
@AdminEntityDefinition(entity=Reservation.class)
public class ReservationDef implements AdminEntityConfiguration {

	@Autowired
	private UserFormatter userFormatter;
	
	@Autowired
	private RoomFormatter roomFormatter;
	
	@Autowired
	private DateTimeFormatter dateTimeFormatter;
	
	@Autowired
	private NullFormatter nullFormatter;
	
	
	
	@Override
	public String group() {
		return "Reservations";
	}
	
	
	
	@Override
	public FieldList fieldList() {
		return FieldList.builder()
				.field("guest", "Username")
				.field("room")
				.field("status")
				.field("checkIn")
				.field("checkOut")
				.build();
	}
	
	
	
	@Override
	public FieldSet fieldSet() {
		FieldSet fieldSet = new FieldSet.Builder()
				.set("Reservation")
					.field("status")
					.field("checkIn")
					.field("checkOut")
				.set("Owner")
					.field("guest", "Username")
					.field("room", "Room ID")
				.build();
		
		return fieldSet;
	}
	
	
	
	@Override
	public void initWebRequestDataBinder(WebRequestDataBinder binder) {
		if (binder == null) {
			throw new NullPointerException("WebRequestDataBinder 가 null 입니다.");
		}
		
		binder.addCustomFormatter(userFormatter, "guest");
		binder.addCustomFormatter(roomFormatter, "room");
		binder.addCustomFormatter(dateTimeFormatter);
		binder.addCustomFormatter(nullFormatter);
		binder.setValidator(ValidatorUtils.getValidator());
	}
	
}

package com.clone.airbnb.def;

import org.springframework.web.bind.support.WebRequestDataBinder;

import com.clone.airbnb.admin.annotation.AdminEntityDefinition;
import com.clone.airbnb.admin.schema.AdminEntityConfiguration;
import com.clone.airbnb.admin.schema.vo.FieldList;
import com.clone.airbnb.admin.schema.vo.FieldSet;
import com.clone.airbnb.entity.Reservation;
import com.clone.airbnb.formatter.DateTimeFormatter;
import com.clone.airbnb.formatter.NullFormatter;
import com.clone.airbnb.formatter.RoomFormatter;
import com.clone.airbnb.formatter.SafeUserByUsernameFormatter;
import com.clone.airbnb.utils.ValidatorUtils;



@AdminEntityDefinition(entity=Reservation.class)
public class ReservationDef implements AdminEntityConfiguration {

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
		
		binder.addCustomFormatter(new SafeUserByUsernameFormatter(), "guest");
		binder.addCustomFormatter(new RoomFormatter(), "room");
		binder.addCustomFormatter(new DateTimeFormatter());
		binder.addCustomFormatter(new NullFormatter());
		binder.setValidator(ValidatorUtils.getValidator());
	}
	
}

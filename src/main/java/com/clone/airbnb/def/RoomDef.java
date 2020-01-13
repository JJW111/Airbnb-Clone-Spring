package com.clone.airbnb.def;

import org.springframework.web.bind.support.WebRequestDataBinder;

import com.clone.airbnb.admin.annotation.AdminEntityDefinition;
import com.clone.airbnb.admin.schema.AdminEntityConfiguration;
import com.clone.airbnb.admin.schema.vo.FieldList;
import com.clone.airbnb.admin.schema.vo.FieldSet;
import com.clone.airbnb.entity.Room;
import com.clone.airbnb.formatter.AmenityFormatter;
import com.clone.airbnb.formatter.DateTimeFormatter;
import com.clone.airbnb.formatter.FacilityFormatter;
import com.clone.airbnb.formatter.HouseRuleFormatter;
import com.clone.airbnb.formatter.NullFormatter;
import com.clone.airbnb.formatter.RoomTypeFormatter;
import com.clone.airbnb.formatter.SafeUserFormatter;
import com.clone.airbnb.utils.ValidatorUtils;

@AdminEntityDefinition(entity=Room.class)
public class RoomDef implements AdminEntityConfiguration {

	@Override
	public String group() {
		return "Rooms";
	}
	
	
	
	@Override
	public FieldList fieldList() {
		return FieldList.builder()
				.field("photos", "Photo")
				.field("name", "Room Name")
				.field("host")
				.field("city")
				.field("country")
				.field("address")
				.field("price")
				.field("roomType")
				.field("totalRating", "T Rating.")
				.field("instantBook")
				.build();
	}
	
	
	
	@Override
	public FieldSet fieldSet() {
		FieldSet fieldSet = new FieldSet.Builder()
				.set("Room Default Information")
					.field("name", "Room Name")
					.field("country")
					.field("city")
					.field("address")
					.field("price")
					.field("host", "Host Username")
				.set("Room Spec")
					.field("roomType")
					.field("facilities")
					.field("amenities")
					.field("houseRules")
				.set("Detail Spec")
					.field("guests")
					.field("beds")
					.field("bedrooms")
					.field("baths")
				.set("Reservation")
					.field("checkIn")
					.field("checkOut")
					.field("instantBook")
				.set("Description")
					.field("description")
				.set("Photos")
					.field("photos")
				.build();
		
		return fieldSet;
	}
	
	
	
	@Override
	public void initWebRequestDataBinder(WebRequestDataBinder binder) {
		if (binder == null) {
			throw new NullPointerException("WebRequestDataBinder 가 null 입니다.");
		}
		
		binder.addCustomFormatter(new SafeUserFormatter(), "host");
		binder.addCustomFormatter(new RoomTypeFormatter(), "roomType");
		binder.addCustomFormatter(new FacilityFormatter(), "facilities");
		binder.addCustomFormatter(new AmenityFormatter(), "amenities");
		binder.addCustomFormatter(new HouseRuleFormatter(), "houseRules");
		binder.addCustomFormatter(new DateTimeFormatter());
		binder.addCustomFormatter(new NullFormatter());
		binder.setValidator(ValidatorUtils.getValidator());
	}
	
}

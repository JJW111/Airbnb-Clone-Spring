package com.clone.airbnb.admin.def;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebRequestDataBinder;

import com.clone.airbnb.admin.annotation.AdminEntityDefinition;
import com.clone.airbnb.admin.schema.AdminEntityConfiguration;
import com.clone.airbnb.admin.schema.vo.FieldList;
import com.clone.airbnb.admin.schema.vo.FieldSet;
import com.clone.airbnb.entity.Room;
import com.clone.airbnb.formatter.AmenitiesFormatter;
import com.clone.airbnb.formatter.DateTimeFormatter;
import com.clone.airbnb.formatter.FacilitiesFormatter;
import com.clone.airbnb.formatter.HouseRulesFormatter;
import com.clone.airbnb.formatter.NullFormatter;
import com.clone.airbnb.formatter.RoomTypeFormatter;
import com.clone.airbnb.formatter.UserFormatter;
import com.clone.airbnb.utils.ValidatorUtils;

@Component
@AdminEntityDefinition(entity=Room.class)
public class RoomDef implements AdminEntityConfiguration {

	@Autowired
	private UserFormatter userFormatter;
	
	@Autowired
	private RoomTypeFormatter roomTypeFormatter;
	
	@Autowired
	private FacilitiesFormatter facilitiesFormatter;
	
	@Autowired
	private AmenitiesFormatter amenitiesFormatter;
	
	@Autowired
	private HouseRulesFormatter houseRulesFormatter;
	
	@Autowired
	private DateTimeFormatter dateTimeFormatter;
	
	@Autowired
	private NullFormatter nullFormatter;

	
	
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
		
		binder.addCustomFormatter(userFormatter, "host");
		binder.addCustomFormatter(roomTypeFormatter, "roomType");
		binder.addCustomFormatter(facilitiesFormatter, "facilities");
		binder.addCustomFormatter(amenitiesFormatter, "amenities");
		binder.addCustomFormatter(houseRulesFormatter, "houseRules");
		binder.addCustomFormatter(dateTimeFormatter);
		binder.addCustomFormatter(nullFormatter);
		binder.setValidator(ValidatorUtils.getValidator());
	}
	
}

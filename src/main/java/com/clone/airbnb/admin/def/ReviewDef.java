package com.clone.airbnb.admin.def;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebRequestDataBinder;

import com.clone.airbnb.admin.annotation.AdminEntityDefinition;
import com.clone.airbnb.admin.schema.AdminEntityConfiguration;
import com.clone.airbnb.admin.schema.vo.FieldList;
import com.clone.airbnb.admin.schema.vo.FieldSet;
import com.clone.airbnb.entity.Review;
import com.clone.airbnb.formatter.NullFormatter;
import com.clone.airbnb.formatter.RoomFormatter;
import com.clone.airbnb.formatter.UserFormatter;
import com.clone.airbnb.utils.ValidatorUtils;

@Component
@AdminEntityDefinition(entity=Review.class)
public class ReviewDef implements AdminEntityConfiguration {

	@Autowired
	private UserFormatter userFormmater;
	
	@Autowired
	private RoomFormatter roomFormatter;
	
	@Autowired
	private NullFormatter nullFormatter;
	
	

	@Override
	public String group() {
		return "Reviews";
	}
	
	
	
	@Override
	public FieldList fieldList() {
		return FieldList.builder()
				.field("user", "Username")
				.field("room", "Room ID")
				.field("accuracy")
				.field("communication")
				.field("cleaniness")
				.field("location")
				.field("checkIn")
				.field("value")
				.field("ratingAverage", "Avg.")
				.build();
	}
	
	
	
	@Override
	public FieldSet fieldSet() {
		FieldSet fieldSet = new FieldSet.Builder()
				.set("Review")
					.field("review")
				.set("Rank.")
					.field("accuracy")
					.field("communication")
					.field("cleaniness")
					.field("location")
					.field("checkIn")
					.field("value")
				.set("Owner")
					.field("user", "Username")
					.field("room", "Room ID")
				.build();
		
		return fieldSet;
	}
	
	
	
	@Override
	public void initWebRequestDataBinder(WebRequestDataBinder binder) {
		if (binder == null) {
			throw new NullPointerException("WebRequestDataBinder 가 null 입니다.");
		}
		
		binder.addCustomFormatter(userFormmater, "user");
		binder.addCustomFormatter(roomFormatter, "room");
		binder.addCustomFormatter(nullFormatter);
		binder.setValidator(ValidatorUtils.getValidator());
	}
	
}

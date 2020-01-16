package com.clone.airbnb.def;

import org.springframework.web.bind.support.WebRequestDataBinder;

import com.clone.airbnb.admin.annotation.AdminEntityDefinition;
import com.clone.airbnb.admin.schema.AdminEntityConfiguration;
import com.clone.airbnb.admin.schema.vo.FieldList;
import com.clone.airbnb.admin.schema.vo.FieldSet;
import com.clone.airbnb.entity.Review;
import com.clone.airbnb.formatter.NullFormatter;
import com.clone.airbnb.formatter.RoomFormatter;
import com.clone.airbnb.formatter.SafeUserFormatter;
import com.clone.airbnb.utils.ValidatorUtils;



@AdminEntityDefinition(entity=Review.class)
public class ReviewDef implements AdminEntityConfiguration {

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
		
		binder.addCustomFormatter(new SafeUserFormatter(), "user");
		binder.addCustomFormatter(new RoomFormatter(), "room");
		binder.addCustomFormatter(new NullFormatter());
		binder.setValidator(ValidatorUtils.getValidator());
	}
	
}

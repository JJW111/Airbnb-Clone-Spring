package com.clone.airbnb.admin.def;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebRequestDataBinder;

import com.clone.airbnb.admin.annotation.AdminEntityDefinition;
import com.clone.airbnb.admin.schema.AdminEntityConfiguration;
import com.clone.airbnb.admin.schema.vo.FieldList;
import com.clone.airbnb.admin.schema.vo.FieldSet;
import com.clone.airbnb.entity.Watchlist;
import com.clone.airbnb.formatter.RoomsFormatter;
import com.clone.airbnb.formatter.UsersFormatter;
import com.clone.airbnb.utils.ValidatorUtils;

@Component
@AdminEntityDefinition(entity=Watchlist.class)
public class WatchlistDef implements AdminEntityConfiguration {

	@Autowired
	private UsersFormatter usersFormaater;
	
	@Autowired
	private RoomsFormatter roomsFormatter;
	

	
	@Override
	public String group() {
		return "Watchlists";
	}
	
	
	
	@Override
	public FieldList fieldList() {
		return FieldList.builder()
				.field("name")
				.field("countUsers", "Users. Cnt")
				.field("countRooms", "Rooms. Cnt")
				.build();
	}
	
	
	
	@Override
	public FieldSet fieldSet() {
		FieldSet fieldSet = new FieldSet.Builder()
				.set("List")
					.field("name", "List Name")
					.field("users", "Usernames")
					.field("rooms")
				.build();
		
		return fieldSet;
	}
	
	
	
	@Override
	public void initWebRequestDataBinder(WebRequestDataBinder binder) {
		if (binder == null) {
			throw new NullPointerException("WebRequestDataBinder 가 null 입니다.");
		}
		
		binder.addCustomFormatter(usersFormaater, "users");
		binder.addCustomFormatter(roomsFormatter, "rooms");
		binder.setValidator(ValidatorUtils.getValidator());
	}
	
}

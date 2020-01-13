package com.clone.airbnb.def;

import org.springframework.web.bind.support.WebRequestDataBinder;

import com.clone.airbnb.admin.annotation.AdminEntityDefinition;
import com.clone.airbnb.admin.schema.AdminEntityConfiguration;
import com.clone.airbnb.admin.schema.vo.FieldList;
import com.clone.airbnb.admin.schema.vo.FieldSet;
import com.clone.airbnb.entity.Watchlist;
import com.clone.airbnb.formatter.RoomsFormatter;
import com.clone.airbnb.formatter.SafeUsersFormatter;
import com.clone.airbnb.utils.ValidatorUtils;

@AdminEntityDefinition(entity=Watchlist.class)
public class WatchlistDef implements AdminEntityConfiguration {

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
		
		binder.addCustomFormatter(new SafeUsersFormatter(), "users");
		binder.addCustomFormatter(new RoomsFormatter(), "rooms");
		binder.setValidator(ValidatorUtils.getValidator());
	}
	
}

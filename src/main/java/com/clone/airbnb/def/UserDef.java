package com.clone.airbnb.def;

import org.springframework.web.bind.support.WebRequestDataBinder;

import com.clone.airbnb.admin.annotation.AdminEntityDefinition;
import com.clone.airbnb.admin.schema.AdminEntityConfiguration;
import com.clone.airbnb.admin.schema.vo.FieldList;
import com.clone.airbnb.admin.schema.vo.FieldSet;
import com.clone.airbnb.entity.User;
import com.clone.airbnb.formatter.DateFormatter;
import com.clone.airbnb.formatter.NullFormatter;
import com.clone.airbnb.utils.ValidatorUtils;



@AdminEntityDefinition(entity=User.class)
public class UserDef implements AdminEntityConfiguration {

	@Override
	public String group() {
		return "Users";
	}
	
	
	
	@Override
	public FieldList fieldList() {
		return FieldList.builder()
				.field("avatar")
				.field("username")
				.field("firstName")
				.field("lastName")
				.field("gender")
				.field("language")
				.field("superhost")
				.field("loginMethod")
				.field("emailVerified")
				.field("emailSecret")
				.build();
	}
	
	
	
	@Override
	public FieldSet fieldSet() {
		FieldSet fieldSet = new FieldSet.Builder()
				.set("Default Information")
					.field("username")
					.field("password")
				.set("Custom Profile")
					.field("firstName")
					.field("lastName")
					.field("avatar")
					.field("birthdate")
					.field("gender")
					.field("language")
					.field("currency")
					.field("bio")
					.field("superhost")
					.field("role")
					.field("loginMethod")
				.build();
		
		return fieldSet;
	}
	
	
	
	@Override
	public void initWebRequestDataBinder(WebRequestDataBinder binder) {
		if (binder == null) {
			throw new NullPointerException("WebRequestDataBinder 가 null 입니다.");
		}
		
		binder.addCustomFormatter(new DateFormatter());
		binder.addCustomFormatter(new NullFormatter());
		binder.setBindEmptyMultipartFiles(false);
		binder.setValidator(ValidatorUtils.getValidator());
	}
	
}

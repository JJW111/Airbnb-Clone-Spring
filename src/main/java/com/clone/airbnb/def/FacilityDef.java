package com.clone.airbnb.def;

import org.springframework.web.bind.support.WebRequestDataBinder;

import com.clone.airbnb.admin.annotation.AdminEntityDefinition;
import com.clone.airbnb.admin.schema.AdminEntityConfiguration;
import com.clone.airbnb.admin.schema.vo.FieldList;
import com.clone.airbnb.admin.schema.vo.FieldSet;
import com.clone.airbnb.entity.Facility;
import com.clone.airbnb.formatter.NullFormatter;
import com.clone.airbnb.utils.ValidatorUtils;



@AdminEntityDefinition(entity=Facility.class)
public class FacilityDef implements AdminEntityConfiguration {

	@Override
	public String group() {
		return "Rooms";
	}
	
	
	
	@Override
	public FieldList fieldList() {
		return FieldList.builder()
				.field("name", "Facility")
				.build();
	}
	
	
	
	@Override
	public FieldSet fieldSet() {
		FieldSet fieldSet = new FieldSet.Builder()
				.set("Facility")
					.field("name")
				.build();
		
		return fieldSet;
	}
	
	
	
	@Override
	public void initWebRequestDataBinder(WebRequestDataBinder binder) {
		if (binder == null) {
			throw new NullPointerException("WebRequestDataBinder 가 null 입니다.");
		}
		
		binder.addCustomFormatter(new NullFormatter());
		binder.setValidator(ValidatorUtils.getValidator());
	}
	
}

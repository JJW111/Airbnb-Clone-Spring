package com.clone.airbnb.dto.admin;

import com.clone.airbnb.entity.HouseRule;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HouseRuleDto implements DtoToOriginalSwitcher<HouseRule> {
	private String name;
	
	@Override
    public HouseRule toOriginal() {
		HouseRule o = new HouseRule();
		o.setName(name);
		
		return o;
    }
}

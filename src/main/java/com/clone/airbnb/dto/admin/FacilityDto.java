package com.clone.airbnb.dto.admin;

import com.clone.airbnb.entity.Facility;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FacilityDto implements DtoToOriginalSwitcher<Facility> {
	private String name;
	
	@Override
    public Facility toOriginal() {
		Facility o = new Facility();
		o.setName(name);
		
		return o;
    }
}

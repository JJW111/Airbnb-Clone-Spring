package com.clone.airbnb.dto.admin;

import com.clone.airbnb.entity.Amenity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AmenityDto implements DtoToOriginalSwitcher<Amenity> {
	private String name;
	
	@Override
    public Amenity toOriginal() {
		Amenity o = new Amenity();
		o.setName(name);
		
		return o;
    }
}

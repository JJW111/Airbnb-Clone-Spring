package com.clone.airbnb.dto.admin;

import com.clone.airbnb.entity.RoomType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RoomTypeDto implements DtoToOriginalSwitcher<RoomType> {
	private String name;
	
	@Override
    public RoomType toOriginal() {
		RoomType o = new RoomType();
		o.setName(name);
		
		return o;
    }
}

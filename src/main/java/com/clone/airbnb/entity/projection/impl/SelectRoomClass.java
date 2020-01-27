package com.clone.airbnb.entity.projection.impl;

import java.util.List;

import com.clone.airbnb.entity.projection.SelectRoom;
import com.clone.airbnb.utils.CollectionUtils;

import lombok.Getter;

@Getter
public class SelectRoomClass {
	private Integer id;

	
	public SelectRoomClass(SelectRoom sr) {
		this.id = sr.getId();
	}
	
	
	public static List<SelectRoomClass> toClasses(List<SelectRoom> srList) {
		return CollectionUtils.toEntityList(list -> {
			srList.forEach(e -> {
				list.add(new SelectRoomClass(e));
			});
			return list;
		});
	}
	
	
	@Override
	public boolean equals(Object obj) {
		SelectRoom sr = (SelectRoom) obj;
		if (sr.getId() == null || this.getId() == null) return false;
		if (!sr.getId().equals(this.getId())) return false;
		return true;
	}
}

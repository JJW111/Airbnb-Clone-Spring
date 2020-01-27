package com.clone.airbnb.entity.projection.impl;

import java.util.List;

import com.clone.airbnb.entity.projection.SelectItem;
import com.clone.airbnb.utils.CollectionUtils;

import lombok.Getter;

@Getter
public class SelectItemClass {
	private Integer id;
	private String name;

	
	public SelectItemClass(SelectItem si) {
		this.id = si.getId();
		this.name = si.getName();
	}
	
	
	public static List<SelectItemClass> toClasses(List<SelectItem> siList) {
		return CollectionUtils.toEntityList(list -> {
			siList.forEach(e -> {
				list.add(new SelectItemClass(e));
			});
			return list;
		});
	}
	
	
	@Override
	public boolean equals(Object obj) {
		SelectItem si = (SelectItem) obj;
		if (si.getId() == null || this.getId() == null) return false;
		if (!si.getId().equals(this.getId())) return false;
		return true;
	}
}

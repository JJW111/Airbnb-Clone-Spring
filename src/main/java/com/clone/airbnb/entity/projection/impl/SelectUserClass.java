package com.clone.airbnb.entity.projection.impl;

import java.util.List;

import com.clone.airbnb.entity.User;
import com.clone.airbnb.entity.projection.SelectUser;
import com.clone.airbnb.utils.CollectionUtils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SelectUserClass {
	private Integer id;
	private String username;

	
	
	public SelectUserClass(SelectUser su) {
		this.id = su.getId();
		this.username = su.getUsername();
	}
	
	
	
	public static List<SelectUserClass> toClasses(List<SelectUser> suList) {
		return CollectionUtils.toEntityList(list -> {
			suList.forEach(e -> {
				list.add(new SelectUserClass(e));
			});
			return list;
		});
	}
	
	
	
	@Override
	public boolean equals(Object obj) {
		Integer id = null;
		
		if (obj instanceof SelectUser) {
			id = ((SelectUser) obj).getId();
		} else if (obj instanceof User) {
			id = ((User) obj).getId();
		}
		
		if (id == null || this.getId() == null) return false;
		if (!id.equals(this.getId())) return false;
		return true;
	}
	
}

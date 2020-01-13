package com.clone.airbnb.admin.entity;

public interface AdminFormEntity<T> {
	Integer getId();
	T deepClone();
	void override(T t);
}

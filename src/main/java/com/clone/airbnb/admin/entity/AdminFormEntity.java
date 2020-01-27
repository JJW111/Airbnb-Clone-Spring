package com.clone.airbnb.admin.entity;

import org.springframework.validation.BindingResult;

public interface AdminFormEntity<T> {
	/**
	 * Primary Key 를 반환한다. 
	 * 
	 * @return 정수값
	 */
	Integer getId();
	
	/**
	 * t 엔터티를 현재 엔터티에 덮어쓴다.
	 * 
	 * @param t 덮어쓸 엔터티
	 */
	void override(T t);
	
	/**
	 * 엔터티가 삭제될 때 호출된다.
	 */
	default void beforeDelete() {}
	
	/**
	 * 엔터티가 생성될 때 호출된다.
	 */
	default void beforeCreate() {}
	
	/**
	 * 엔터티가 업데이트될 때 호출된다.
	 */
	default void beforeUpdate(Object old) {}
	
	/**
	 * 어노테이션으로 정의한 Validation 외에
	 * 추가로 검증해야할 유효성 검증을 수행한다.
	 * 
	 * @param result BindingResult 객체
	 */
	default void validate(BindingResult result) {}
}

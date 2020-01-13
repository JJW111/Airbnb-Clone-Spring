package com.clone.airbnb.admin.schema.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * {@link AdminEntity} 객체와 {@link Groups} 객체를 가지고 있다.
 * 
 * @author jjw
 *
 */
@Getter
@AllArgsConstructor
public class AdminDefinitionObject {
	private final AdminEntity adminEntity;
	private final Groups groups;
}

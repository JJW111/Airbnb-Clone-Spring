package com.clone.airbnb.service;

import com.clone.airbnb.entity.User;
 
public interface KakaoLoginService {
	String identityUrl();
	User authorize(String code);
}

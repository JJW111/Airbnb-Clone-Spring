package com.clone.airbnb.service;

import com.clone.airbnb.entity.User;
 
public interface GithubLoginService {
	String identityUrl();
	User authorize(String code);
}

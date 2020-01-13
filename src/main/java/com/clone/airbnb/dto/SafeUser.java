package com.clone.airbnb.dto;

import java.util.Date;

import com.clone.airbnb.admin.entity.FileProjectionFrame;
import com.clone.airbnb.entity.enu.Currency;
import com.clone.airbnb.entity.enu.Gender;
import com.clone.airbnb.entity.enu.Language;
import com.clone.airbnb.entity.enu.LoginMethod;
import com.clone.airbnb.entity.enu.Role;

public interface SafeUser {
	Integer 	getId();
	String 		getUsername();
	String 		getFirstName();
	String 		getLastName();
	FileProjectionFrame getAvatar();
	String 		getBio();
	Date		getBirthdate();
	Gender 		getGender();
	Language 	getLanguage();
	Currency 	getCurrency();
	Boolean 	getSuperhost();
	Role		getRole();
	Date		getCreated();
	Date		getUpdated();
	Boolean		getEmailVerified();
	String		getEmailSecret();
	LoginMethod getLoginMethod();
}

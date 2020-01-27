package com.clone.airbnb.entity.projection.admin;

import java.util.Date;

import com.clone.airbnb.admin.entity.FileProjectionFrame;
import com.clone.airbnb.entity.enu.Currency;
import com.clone.airbnb.entity.enu.Gender;
import com.clone.airbnb.entity.enu.Language;
import com.clone.airbnb.entity.enu.LoginMethod;
import com.clone.airbnb.entity.enu.Role;

public interface UserProjection {
	Integer getId();
	String 	getUsername();
	String 	getPassword();
	String 	getFirstName();
	String 	getLastName();
	FileProjectionFrame getAvatar();
	String	getBio();
	Date	getBirthdate();
	Gender	getGender();
	Language getLanguage();
	Currency getCurrency();
	Boolean	getSuperhost();
	Role	getRole();
	Boolean	getEmailVerified();
	String	getEmailSecret();
	LoginMethod getLoginMethod();
}

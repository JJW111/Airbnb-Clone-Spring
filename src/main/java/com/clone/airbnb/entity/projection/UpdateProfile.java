package com.clone.airbnb.entity.projection;

import java.util.Date;
import com.clone.airbnb.entity.enu.Currency;
import com.clone.airbnb.entity.enu.Gender;
import com.clone.airbnb.entity.enu.Language;
import com.clone.airbnb.entity.enu.LoginMethod;

public interface UpdateProfile {
	Integer		getId();
	String 		getFirstName();
	String 		getLastName();
	String 		getBio();
	Date		getBirthdate();
	Gender 		getGender();
	Language 	getLanguage();
	Currency 	getCurrency();
	LoginMethod getLoginMethod();
}

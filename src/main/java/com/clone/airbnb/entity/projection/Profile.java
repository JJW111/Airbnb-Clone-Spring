package com.clone.airbnb.entity.projection;

public interface Profile {
	String		getUsername();
	String 		getFirstName();
	String		getBio(); 
	PhotoOnlyPath		 getAvatar();
	Boolean 	getSuperhost();
}

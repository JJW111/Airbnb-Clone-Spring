package com.clone.airbnb.entity.projection;

public interface DetailHost {
	Integer		getId();
	String		getUsername();
	String		getFirstName();
	Boolean 	getSuperhost();
	PhotoOnlyPath		getAvatar();
}

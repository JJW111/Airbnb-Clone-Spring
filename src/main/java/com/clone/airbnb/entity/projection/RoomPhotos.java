package com.clone.airbnb.entity.projection;

import java.util.List;

public interface RoomPhotos {
	Integer getId();
	String getName();
	List<Photo> getPhotos();
}

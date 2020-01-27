package com.clone.airbnb.entity.projection.admin;

import java.util.HashMap;
import java.util.Map;


public class ProjectionFactory {

	private static class Singleton {
		private static final Map<String, Class<?>> map = new HashMap<String, Class<?>>();
		
		static {
			map.put("Amenity", ItemProjection.class);
			map.put("Facility", ItemProjection.class);
			map.put("HouseRule", ItemProjection.class);
			map.put("RoomType", ItemProjection.class);
			map.put("Conversation", ConversationProjection.class);
			map.put("Message", MessageProjection.class);
			map.put("Reservation", ReservationProjection.class);
			map.put("User", UserProjection.class);
			map.put("Review", ReviewProjection.class);
			map.put("Room", RoomProjection.class);
			map.put("Watchlist", WatchlistProjection.class);
		}
	}
	
	
	
	public static Class<?> get(String entityName) {
		return Singleton.map.get(entityName);
	}
	
}

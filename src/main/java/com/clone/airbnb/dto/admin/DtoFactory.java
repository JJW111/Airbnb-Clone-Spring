package com.clone.airbnb.dto.admin;

public class DtoFactory {

	private static Object get(String entityName) {
		
		Object dto= null;
		
		switch(entityName) {
		case "User":
			dto = new UserAddDto();
			break;
		case "Room":
			dto = new RoomDto();
			break;
		case "Amenity":
			dto = new AmenityDto();
			break;
		case "Facility":
			dto = new FacilityDto();
			break;
		case "HouseRule":
			dto = new HouseRuleDto();
			break;
		case "RoomType":
			dto = new RoomTypeDto();
			break;
		case "Conversation":
			dto = new ConversationDto();
			break;
		case "Message":
			dto = new MessageDto();
			break;
		case "Reservation":
			dto = new ReservationDto();
			break;
		case "Review":
			dto = new ReviewDto();
			break;
		case "Watchlist":
			dto = new WatchlistDto();
			break;
			default:
				break;
		}

		return dto;
		
	}
	
	
	public static Object get(String entityName, Edit edit) {
		
		Object dto = null;
		
		if ("User".equals(entityName)) {
			if (edit.equals(Edit.ADD)) {
				dto = get(entityName);
			} else if (edit.equals(Edit.UPDATE)) {
				dto = new UserUpdateDto();
			}
		} else {
			dto = get(entityName); 
		}
		
		return dto;
		
	}
	
}

package com.clone.airbnb.dto;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import com.clone.airbnb.common.Common;
import com.clone.airbnb.entity.Amenity;
import com.clone.airbnb.entity.Facility;
import com.clone.airbnb.entity.HouseRule;
import com.clone.airbnb.entity.Room;
import com.clone.airbnb.entity.RoomType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
 
@Getter
@Setter
@ToString
public class RoomAddDto {
    @Size(max = 100)
	private String name;
	@Size(max = 100)
	private String description;
	@NotBlank
	private String address;
	@NotBlank
	private String city;
	@NotBlank
	private String country;
	@NotNull
	@Min(value=0)  
    @Max(value=10000)  
	private Integer price;
	@NotNull
	@Min(value=0)  
    @Max(value=20)
	private Integer guests;
	@NotNull
	@Min(value=0)  
    @Max(value=8)
	private Integer beds;
	@NotNull
	@Min(value=0)  
    @Max(value=5)
	private Integer bedrooms;
	@NotNull
	@Min(value=0)  
    @Max(value=3)
	private Integer baths;
	@NotNull
	@DateTimeFormat(pattern = Common.DATETIME_FORMAT)
	private Date checkIn;
	@NotNull
	@DateTimeFormat(pattern = Common.DATETIME_FORMAT)
	private Date checkOut;
	private Boolean instantBook = false;
	@NotNull(message = "Room Type을  선택하여 주십시오.")
	private RoomType roomType;
	private List<Amenity> amenities;
	private List<Facility> facilities;
	private List<HouseRule> houseRules;
	private List<MultipartFile> photos;
	
	public Room toOriginal() {
		Room room = new Room();
		room.setName(this.getName());
		room.setDescription(this.getDescription());
		room.setAddress(this.getAddress());
		room.setCity(this.getCity());
		room.setCountry(this.getCountry());
		room.setPrice(this.getPrice());
		room.setGuests(this.getGuests());
		room.setBeds(this.getBeds());
		room.setBedrooms(this.getBedrooms());
		room.setBaths(this.getBaths());
		room.setCheckIn(this.getCheckIn());
		room.setCheckOut(this.getCheckOut());
		room.setInstantBook(this.getInstantBook());
		room.setRoomType(this.getRoomType());
		room.setFacilities(this.getFacilities());
		room.setAmenities(this.getAmenities());
		room.setHouseRules(this.getHouseRules());
		room.setPhotosByFiles(this.getPhotos());
		
		return room;
	}
}


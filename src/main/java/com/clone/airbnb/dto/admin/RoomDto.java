package com.clone.airbnb.dto.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import com.clone.airbnb.admin.form.annotation.EntityForm;
import com.clone.airbnb.common.Common;
import com.clone.airbnb.entity.Amenity;
import com.clone.airbnb.entity.Facility;
import com.clone.airbnb.entity.HouseRule;
import com.clone.airbnb.entity.Room;
import com.clone.airbnb.entity.RoomType;
import com.clone.airbnb.entity.User;
import com.clone.airbnb.entity.file.RoomPhoto;
import com.clone.airbnb.utils.ValidUtils;

import lombok.Getter;
import lombok.Setter;
 
@EntityForm
@Entity
@Table(name = "room")
@Getter
@Setter
public class RoomDto implements DtoToOriginalSwitcher<Room> {
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
	@Min(value=0, message="0보다 커야합니다.")  
    @Max(value=10000, message="10,000보다 작아야 합니다.")  
	private Integer price;
	@NotNull
	@Min(value=0, message="must be equal or less than 0")  
    @Max(value=20, message="must be equal or less than 20")
	private Integer guests;
	@NotNull
	@Min(value=0, message="must be equal or less than 0")  
    @Max(value=8, message="must be equal or less than 8")
	private Integer beds;
	@NotNull
	@Min(value=0, message="must be equal or less than 0")  
    @Max(value=5, message="must be equal or less than 5")
	private Integer bedrooms;
	@NotNull
	@Min(value=0, message="must be equal or less than 0")  
    @Max(value=5, message="must be equal or less than 5")
	private Integer baths;
	@DateTimeFormat(pattern = Common.DATETIME_FORMAT)
	@NotNull
	private Date checkIn;
	@DateTimeFormat(pattern = Common.DATETIME_FORMAT)
	@NotNull
	private Date checkOut;
	private Boolean instantBook = false;
	@NotNull(message = "호스트가 존재하지 않습니다.")
	private User host;
	@NotNull(message = "Room Type을  선택하여 주십시오.")
	private RoomType roomType;
	private List<Facility> facilities;
	private List<Amenity> amenities;
	private List<HouseRule> houseRules;
	private List<RoomPhoto> photos;
	private List<MultipartFile> photoFiles;
	
    
    @Override
    public Room toOriginal() {
    	Room room = new Room();
    	room.setName(this.getName());
    	room.setDescription(this.getDescription());
    	room.setAddress(this.getAddress());
    	room.setCountry(this.getCountry());
    	room.setCity(this.getCity());
    	room.setPrice(this.getPrice());
    	room.setGuests(this.getGuests());
    	room.setBeds(this.getBeds());
    	room.setBedrooms(this.getBedrooms());
    	room.setBaths(this.getBaths());
    	room.setCheckIn(this.getCheckIn());
    	room.setCheckOut(this.getCheckOut());
    	room.setInstantBook(this.getInstantBook());
    	room.setHost(this.getHost());
    	room.setRoomType(this.getRoomType());
    	room.setAmenities(this.getAmenities());
    	room.setFacilities(this.getFacilities());
    	room.setHouseRules(this.getHouseRules());
    	if (ValidUtils.isValid(this.getPhotoFiles())) {
    		List<RoomPhoto> photos = this.getPhotos();
    		
    		if (photos == null) {
    			photos = new ArrayList<>();
    		}
    		
    		for (int i = 0; i < this.getPhotoFiles().size(); i++) {
    			MultipartFile file = this.getPhotoFiles().get(i);
    			if (ValidUtils.isValid(file)) {
	    			RoomPhoto photo = new RoomPhoto();
	    			photo.setFile(file);
	    			photos.add(photo);
    			}
    		}
    		
    		this.setPhotos(photos);
    	}
    	room.setPhotos(this.getPhotos());

    	return room;
    }
    
}


package com.clone.airbnb.dto;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.clone.airbnb.entity.Amenity;
import com.clone.airbnb.entity.Facility;
import com.clone.airbnb.entity.HouseRule;
import com.clone.airbnb.entity.RoomType;
import lombok.Getter;
import lombok.Setter;
 
@Getter
@Setter
public class RoomUpdateDto {
	@NotNull
	private Integer id;
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
	private Boolean instantBook = false;
	@NotNull(message = "Room Type을  선택하여 주십시오.")
	private RoomType roomType;
	private List<Facility> facilities;
	private List<Amenity> amenities;
	private List<HouseRule> houseRules;
}


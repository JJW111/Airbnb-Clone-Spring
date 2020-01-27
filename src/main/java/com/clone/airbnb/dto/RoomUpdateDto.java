package com.clone.airbnb.dto;

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
import com.clone.airbnb.admin.form.annotation.EntityForm;
import com.clone.airbnb.common.Common;
import com.clone.airbnb.entity.Amenity;
import com.clone.airbnb.entity.Facility;
import com.clone.airbnb.entity.HouseRule;
import com.clone.airbnb.entity.RoomType;
import lombok.Getter;
import lombok.Setter;
 
@EntityForm
@Entity
@Table(name = "room")
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
	@NotNull
	@DateTimeFormat(pattern = Common.DATETIME_FORMAT)
	private Date checkIn;
	@NotNull
	@DateTimeFormat(pattern = Common.DATETIME_FORMAT)
	private Date checkOut;
	private Boolean instantBook = false;
	@NotNull(message = "Room Type을  선택하여 주십시오.")
	private RoomType roomType;
	private List<Facility> facilities;
	private List<Amenity> amenities;
	private List<HouseRule> houseRules;
}


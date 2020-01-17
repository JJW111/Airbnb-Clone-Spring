package com.clone.airbnb.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import com.clone.airbnb.admin.entity.AdminFormEntity;
import com.clone.airbnb.admin.form.annotation.CheckBoxForm;
import com.clone.airbnb.admin.form.annotation.DatetimeForm;
import com.clone.airbnb.admin.form.annotation.EntityForm;
import com.clone.airbnb.admin.form.annotation.IntegerForm;
import com.clone.airbnb.admin.form.annotation.JoinManyForm;
import com.clone.airbnb.admin.form.annotation.MapSelectBoxForm;
import com.clone.airbnb.admin.form.annotation.MultipleImageUploadForm;
import com.clone.airbnb.admin.form.annotation.JoinOneForm;
import com.clone.airbnb.admin.form.annotation.TextAreaForm;
import com.clone.airbnb.admin.form.annotation.TextForm;
import com.clone.airbnb.common.Common;
import com.clone.airbnb.dto.SafeUser;
import com.clone.airbnb.entity.file.RoomPhoto;
import com.clone.airbnb.entity.sup.DateTimeModel;
import com.clone.airbnb.repository.AmenityRepository;
import com.clone.airbnb.repository.FacilityRepository;
import com.clone.airbnb.repository.HouseRuleRepository;
import com.clone.airbnb.repository.RoomTypeRepository;
import com.clone.airbnb.repository.UserRepository;
import com.clone.airbnb.utils.CountryUtils;
import com.clone.airbnb.utils.FileUtils;
import com.clone.airbnb.utils.ValidUtils;
import com.clone.airbnb.utils.WordUtils;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
 
@EntityForm
@Entity
@Table(name = "room")
@ToString(exclude = { "host", "reviews" }, callSuper = true)
@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Room extends DateTimeModel implements AdminFormEntity<Room> {
	
	@Id
    @GeneratedValue
    private Integer id;
	
	
	
	@TextForm
	@Column(nullable = false)
	private String name;
	
	
	
	@TextAreaForm
	@Length(max = 100)
	private String description;
	
	
	
	@TextForm
	@Column(nullable = false)
	private String address;
	
	
	
	@TextForm
	@Column(nullable = false)
	private String city;
	
	
	
	@MapSelectBoxForm(blank = false, defaultOption = "----- Countries -----", method = "countries")
	@Column(nullable = false)
	private String country;
	
	
	
	@IntegerForm(blank = false)
	@Column(nullable = false)
	private Integer price;
	
	
	
	@IntegerForm(blank = false)
	@Column(nullable = false)
	private Integer guests;
	
	
	
	@IntegerForm(blank = false)
	@Column(nullable = false)
	private Integer beds;
	
	
	
	@IntegerForm(blank = false)
	@Column(nullable = false)
	private Integer bedrooms;
	
	
	
	@IntegerForm(blank = false)
	@Column(nullable = false)
	private Integer baths;
	
	
	
	@DatetimeForm(blank = false)
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date checkIn;
	
	
	
	@DatetimeForm(blank = false)
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date checkOut;
	
	
	
	@CheckBoxForm
	@Column(nullable = false)
	private Boolean instantBook;
	
	
	
	@JoinOneForm(blank = false, field = "username", repository = UserRepository.class, defaultOption = "------ Select User ------")
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(referencedColumnName = "id", nullable = false)
	private User host;
	
	
	
	
	
	@JoinOneForm(field = "name", defaultOption = "Room Type", repository = RoomTypeRepository.class, blank = false)
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(referencedColumnName="id", nullable = false)
	private RoomType roomType;
	
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "room", cascade = CascadeType.REMOVE)
	private List<Review> reviews;
	
	
	
	
	@JoinManyForm(field = "name", defaultOption = "----- Select More Than One Facilities -----", repository = FacilityRepository.class, blank = false)
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			  name = "room_facility",
			  joinColumns = @JoinColumn(name = "room_id"), 
			  inverseJoinColumns = @JoinColumn(name = "facility_id"))
	private List<Facility> facilities;
	
	
	
	
	
	@JoinManyForm(field = "name", defaultOption = "----- Select More Than One Amenities -----", repository = AmenityRepository.class, blank = false)
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			  name = "room_amenity",
			  joinColumns = @JoinColumn(name = "room_id"), 
			  inverseJoinColumns = @JoinColumn(name = "amenity_id"))
	private List<Amenity> amenities;
	
	
	
	

	@JoinManyForm(field = "name", defaultOption = "----- Select More Than One House Rules -----", repository = HouseRuleRepository.class, blank = false)
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			  name = "room_houserule",
			  joinColumns = @JoinColumn(name = "room_id"), 
			  inverseJoinColumns = @JoinColumn(name = "houserule_id"))
	private List<HouseRule> houseRules;
	
	
	
	
	@MultipleImageUploadForm(formName = "photoFiles")
	@OneToMany(mappedBy="room", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<RoomPhoto> photos;
	
	
	
	
	@OneToMany(mappedBy = "room", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Reservation> reservations;
	
	
	
	
	private void setPhotos(List<RoomPhoto> photos) {
		if (photos == null) return;
		if (this.photos == null) {
			this.photos = new ArrayList<>();
		}
		this.photos.clear();
		this.photos.addAll(photos);
	}
	
	
	
	
	private void setReviews(List<Review> reviews) {
		if (reviews == null) return;
		if (this.reviews == null) {
			this.reviews = new ArrayList<>();
		}
		this.reviews.clear();
		this.reviews.addAll(reviews);
	}
	
	
	
	
	private void setReservations(List<Reservation> reservations) {
		if (reservations == null) return;
		if (this.reservations == null) {
			this.reservations = new ArrayList<>();
		}
		this.reservations.clear();
		this.reservations.addAll(reservations);
	}
	
	
	
	
	/******** Builder 클래스 선언 **********/
    @Getter
    @ToString
    public static class Builder {
    	private Integer id;
    	@NotBlank
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
    	@Min(value=0, message="must be equal or less than 0")  
        @Max(value=10000000, message="must be equal or less than 10000000")  
    	private Integer price;
    	@NotNull
    	@Min(value=0, message="must be equal or less than 0")  
        @Max(value=100, message="must be equal or less than 100")
    	private Integer guests;
    	@NotNull
    	@Min(value=0, message="must be equal or less than 0")  
        @Max(value=100, message="must be equal or less than 100")
    	private Integer beds;
    	@NotNull
    	@Min(value=0, message="must be equal or less than 0")  
        @Max(value=100, message="must be equal or less than 100")
    	private Integer bedrooms;
    	@NotNull
    	@Min(value=0, message="must be equal or less than 0")  
        @Max(value=100, message="must be equal or less than 100")
    	private Integer baths;
    	@DateTimeFormat(pattern = Common.DATETIME_FORMAT)
    	@NotNull
    	private Date checkIn;
    	@DateTimeFormat(pattern = Common.DATETIME_FORMAT)
    	@NotNull
    	private Date checkOut;
    	@NotNull
    	private Boolean instantBook;
    	@NotNull(message = "호스트가 존재하지 않습니다.")
    	private SafeUser host;
    	@NotNull(message = "Room Type을  선택하여 주십시오.")
    	private RoomType roomType;
    	private List<Review> reviews;
    	private List<Facility> facilities;
    	private List<Amenity> amenities;
    	private List<HouseRule> houseRules;
    	private List<RoomPhoto> photos;
    	private List<MultipartFile> photoFiles;
    	private List<Reservation> reservations;
    	private Date created;
        private Date updated;
        
    	
    	
    	public Builder setId(Integer id) {
    		this.id = id;
    		return this;
    	}
    	
    	
    	
    	public Builder setName(String name) {
    		this.name = name;
    		return this;
    	}
    	
    	
    	
    	public Builder setDescription(String description) {
    		this.description = description;
    		return this;
    	}
    	
    	
    	
    	public Builder setAddress(String address) {
    		this.address = address;
    		return this;
    	}
    	
    	
    	
    	public Builder setCity(String city) {
    		this.city = city;
    		return this;
    	}
    	
    	
    	
    	public Builder setCountry(String country) {
    		this.country = country;
    		return this;
    	}
    	
    	
    	
    	public Builder setPrice(Integer price) {
    		this.price = price;
    		return this;
    	}
    	
    	
    	
    	public Builder setGuests(Integer guests) {
    		this.guests = guests;
    		return this;
    	}
    	
    	
    	
    	public Builder setBeds(Integer beds) {
    		this.beds = beds;
    		return this;
    	}
    	
    	
    	
    	public Builder setBedrooms(Integer bedrooms) {
    		this.bedrooms = bedrooms;
    		return this;
    	}
    	
    	
    	
    	public Builder setBaths(Integer baths) {
    		this.baths = baths;
    		return this;
    	}
    	
    	
    	
    	public Builder setCheckIn(Date checkIn) {
    		this.checkIn = checkIn;
    		return this;
    	}
    	
    	
    	
    	public Builder setCheckOut(Date checkOut) {
    		this.checkOut = checkOut;
    		return this;
    	}
    	
    	
    	
    	public Builder setInstantBook(Boolean instantBook) {
    		this.instantBook = instantBook;
    		return this;
    	}
    	
    	
    	
    	public Builder setHost(SafeUser host) {
    		this.host = host;
    		return this;
    	}
    	
    	
    	
    	public Builder setRoomType(RoomType roomType) {
    		this.roomType = roomType;
    		return this;
    	}
    	
    	
    	
    	public Builder setReviews(List<Review> reviews) {
    		this.reviews = reviews;
    		return this;
    	}
    	
    	
    	
    	public Builder setFacilities(List<Facility> facilities) {
    		this.facilities = facilities;
    		return this;
    	}
    	
    	
    	
    	public Builder setAmenities(List<Amenity> amenities) {
    		this.amenities = amenities;
    		return this;
    	}
    	
    	
    	
    	public Builder setHouseRules(List<HouseRule> houseRules) {
    		this.houseRules = houseRules;
    		return this;
    	}
    	
    	
    	
    	public Builder setPhotos(List<RoomPhoto> photos) {
    		this.photos = photos;
    		return this;
    	}
    	
    	
    	
    	public Builder setPhotoFiles(List<MultipartFile> photoFiles) {
    		this.photoFiles = photoFiles;
    		return this;
    	}
    	
    	
    	
    	public Builder setReservations(List<Reservation> reservations) {
			this.reservations = reservations;
			return this;
		}
    	
    	
    	
    	public Builder setCreated(Date created) {
			this.created = created;
			return this;
		}
		
		
		
		public Builder setUpdated(Date updated) {
			this.updated = updated;
			return this;
		}
    	
    	
    	
    	public Room build() {
    		return new Room(this);
    	}
    	
    }
    
    
    
    private Room(Builder builder) {
    	this.setId(builder.getId());
    	this.setName(builder.getName());
    	this.setDescription(builder.getDescription());
    	this.setAddress(builder.getAddress());
    	this.setCity(WordUtils.capitalize(builder.getCity()));
    	this.setCountry(builder.getCountry());
    	this.setPrice(builder.getPrice());
    	this.setGuests(builder.getGuests());
    	this.setBeds(builder.getBeds());
    	this.setBedrooms(builder.getBedrooms());
    	this.setBaths(builder.getBaths());
    	this.setCheckIn(builder.getCheckIn());
    	this.setCheckOut(builder.getCheckOut());
    	this.setInstantBook(builder.getInstantBook());
    	this.setRoomType(builder.getRoomType());
    	this.setReviews(builder.getReviews());
    	this.setFacilities(builder.getFacilities());
    	this.setAmenities(builder.getAmenities());
    	this.setHouseRules(builder.getHouseRules());
    	this.setHost(User.toUser(builder.getHost()));
    	List<RoomPhoto> photoList = new ArrayList<>();
    	if (ValidUtils.isValid(builder.getPhotos())) {
	    	for (RoomPhoto p : builder.getPhotos()) {
	    		if (ValidUtils.isValid(p)) {
	    			photoList.add(p.toBuilder().setRoom(this).build());
	    		}
	    	}
    	}
    	if (ValidUtils.isValid(builder.getPhotoFiles())) {
	    	for (MultipartFile f : builder.getPhotoFiles()) {
	    		if (ValidUtils.isValid(f)) {
	    			photoList.add(RoomPhoto.builder()
	    					.setFile(f)
	    					.setRoom(this)
	    					.build());
	    		}
	    	}
    	}
    	this.setPhotos(photoList);
    	this.setReservations(builder.getReservations());
    	this.setCreated(builder.getCreated());
		this.setUpdated(builder.getUpdated());
    }
    
    
    
    public static Builder builder() {
    	return new Builder();
    }
    
    
    
    public Builder toBuilder() {
    	SafeUser safeUser = null;
		
		if (this.getHost() != null) {
			safeUser = this.getHost().toSafeUser();
		}
		
    	return builder()
    			.setId(this.getId())
				.setName(this.getName())
				.setDescription(this.getDescription())
				.setAddress(this.getAddress())
				.setCity(this.getCity())
				.setCountry(this.getCountry())
				.setPrice(this.getPrice())
				.setGuests(this.getGuests())
				.setBeds(this.getBeds())
				.setBedrooms(this.getBedrooms())
				.setBaths(this.getBaths())
				.setCheckIn(this.getCheckIn())
				.setCheckOut(this.getCheckOut())
				.setInstantBook(this.getInstantBook())
				.setRoomType(this.getRoomType())
				.setReviews(this.getReviews())
				.setAmenities(this.getAmenities())
				.setFacilities(this.getFacilities())
				.setHouseRules(this.getHouseRules())
				.setHost(safeUser)
				.setPhotos(this.getPhotos())
				.setReservations(this.getReservations())
				.setCreated(this.getCreated())
				.setUpdated(this.getUpdated());
    }
    
    
    
	@Override
	public Room deepClone() {
		return this.toBuilder().build();
	}
	
	
	
	
	@Override
	public void override(Room t) {
		if (t.getId() 				!= null) this.setId(t.getId());
    	if (t.getName() 			!= null) this.setName(t.getName());
    	if (t.getDescription() 		!= null) this.setDescription(t.getDescription());
    	if (t.getAddress()			!= null) this.setAddress(t.getAddress());
    	if (t.getCity() 			!= null) this.setCity(t.getCity());
    	if (t.getCountry() 			!= null) this.setCountry(t.getCountry());
    	if (t.getPrice()			!= null) this.setPrice(t.getPrice());
    	if (t.getGuests()			!= null) this.setGuests(t.getGuests());
    	if (t.getBeds()				!= null) this.setBeds(t.getBeds());
    	if (t.getBedrooms()			!= null) this.setBedrooms(t.getBedrooms());
    	if (t.getBaths()			!= null) this.setBaths(t.getBaths());
    	if (t.getCheckIn()			!= null) this.setCheckIn(t.getCheckIn());
    	if (t.getCheckOut()			!= null) this.setCheckOut(t.getCheckOut());
    	if (t.getInstantBook()		!= null) this.setInstantBook(t.getInstantBook());
    	if (t.getHost()				!= null) this.setHost(t.getHost());
    	if (t.getRoomType()			!= null) this.setRoomType(t.getRoomType());
    	if (t.getReviews()			!= null) this.setReviews(t.getReviews());
    	if (t.getFacilities()		!= null) this.setFacilities(t.getFacilities());
    	if (t.getAmenities()		!= null) this.setAmenities(t.getAmenities());
    	if (t.getHouseRules()		!= null) this.setHouseRules(t.getHouseRules());
    	if (t.getPhotos() 			!= null) this.setPhotos(t.getPhotos());
    	if (t.getReservations()		!= null) this.setReservations(t.getReservations());
    	if (t.getCreated()			!= null) this.setCreated(t.getCreated());
    	if (t.getUpdated()			!= null) this.setUpdated(t.getUpdated());
    	this.setCreated(super.getCreated());
    	this.setUpdated(super.getUpdated());
	}
	
	
	
	public Map<String, String> countries() {
		return CountryUtils.countries();
	}
	
	
	public Double totalRating() {
		Double total = 0.0;
		Double totalRating = 0.0;
		
		if (this.reviews != null && !this.reviews.isEmpty()) {
			for (int i = 0; i < this.reviews.size(); i++) {
				total += this.reviews.get(i).ratingAverage();
			}
			
			totalRating = Math.round(total * 100 / this.reviews.size()) / 100.0;
		}
		
		return totalRating;
	}
	
	
	@Override
	public void beforeCreate() {
		saveFiles(this);
	}
	
	
	@Override
	public void beforeDelete() {
		deleteFiles(this);
	}
	
	
	@Override
	public void beforeUpdate(Object old) {
		Room oldRoom = (Room) old;
		
		saveFiles(this);
		
		if (ValidUtils.isValid(oldRoom.getPhotos())) {
			oldRoom.getPhotos().removeAll(this.getPhotos());
			deleteFiles(oldRoom);
		}
	}
	
	
	
	@Override
	public void validate(BindingResult result) {
		if (ValidUtils.isValid(this.getPhotos())) {
			if (this.getPhotos().size() < 5 || this.getPhotos().size() > 10) {
				result.rejectValue("photoFiles", "validation.room.photos.size.mismatch");
			}
		}
	}
	
	
	
	private void saveFiles(Room r) {
		if (ValidUtils.isValid(r.getPhotos())) {
			this.getPhotos().forEach(e -> {
				if (ValidUtils.isValid(e)) {
					if (ValidUtils.isValid(e.getFile())) {
						FileUtils.save(e.getFile(), e.getUploadPath());
					}
				}
			});
		}
	}
	
	
	private void deleteFiles(Room r) {
		if (ValidUtils.isValid(r.getPhotos())) {
			r.getPhotos().forEach(e -> {
				if (ValidUtils.isValid(e.getUploadPath())) {
					FileUtils.delete(e.getUploadPath());
				}
			});
		}
	}
	
}


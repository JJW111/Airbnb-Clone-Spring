package com.clone.airbnb.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.clone.airbnb.entity.file.RoomPhoto;
import com.clone.airbnb.entity.sup.DateTimeModel;
import com.clone.airbnb.utils.FileUtils;
import com.clone.airbnb.utils.ValidUtils;

import lombok.Getter;
import lombok.Setter;
 
@EntityForm
@Entity
@Table(name = "room")
@Getter
@Setter
public class Room extends DateTimeModel implements AdminFormEntity<Room> {
	
	@Id
    @GeneratedValue
    private Integer id;
	
	
	
	@TextForm
	@Column(nullable = false)
	private String name;
	
	
	
	@TextAreaForm
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
	private Boolean instantBook = false;
	
	
	
	@JoinOneForm(blank = false, itemLabel = "username", itemValue="id", method="users", defaultOption = "------ Select User ------")
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(referencedColumnName = "id", nullable = false)
	private User host;
	
	
	
	
	
	@JoinOneForm(blank = false, itemLabel = "name", itemValue="id", method="roomTypes", defaultOption = "Room Type")
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(referencedColumnName="id", nullable = false)
	private RoomType roomType;
	
	
	
	
	@JoinManyForm(blank = true, itemLabel = "name", itemValue = "id", method = "amenities", defaultOption = "----- Select Amenities -----")
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			  name = "room_amenity",
			  joinColumns = @JoinColumn(name = "room_id"), 
			  inverseJoinColumns = @JoinColumn(name = "amenity_id"))
	private List<Amenity> amenities;
	
	
	
	
	@JoinManyForm(blank = true, itemLabel = "name", itemValue = "id", method = "facilities", defaultOption = "----- Select Facilities -----")
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			  name = "room_facility",
			  joinColumns = @JoinColumn(name = "room_id"), 
			  inverseJoinColumns = @JoinColumn(name = "facility_id"))
	private List<Facility> facilities;
	
	
	
	
	
	@JoinManyForm(blank = true, itemLabel = "name", itemValue = "id", method = "houseRules", defaultOption = "----- Select House Rules -----")
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			  name = "room_houserule",
			  joinColumns = @JoinColumn(name = "room_id"), 
			  inverseJoinColumns = @JoinColumn(name = "houserule_id"))
	private List<HouseRule> houseRules;
	
	
	
	@MultipleImageUploadForm(fileFormName = "photoFiles")
	@OneToMany(mappedBy="room", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<RoomPhoto> photos;
	
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "room", cascade = CascadeType.REMOVE)
	private List<Review> reviews;
	
	
	
	@OneToMany(mappedBy = "room", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Reservation> reservations;
	
	
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinTable(
			  name = "watchlist_room",
			  joinColumns = @JoinColumn(name = "room_id"), 
			  inverseJoinColumns = @JoinColumn(name = "watchlist_id"))
    private List<Watchlist> watchlists;
	
	
	
	public void setPhotos(List<RoomPhoto> photos) {
		if (photos == null) return;
		if (this.photos == null) {
			this.photos = new ArrayList<>();
		}
		photos.forEach(e -> {
			e.setRoom(this);
		});
		this.photos.clear();
		this.photos.addAll(photos);
	}
	
	
	
	
	public void setPhotosByFiles(List<MultipartFile> photoFiles) {
		if (photoFiles == null) return;
		final List<RoomPhoto> photos = new ArrayList<>();
		photoFiles.forEach(e -> {
			RoomPhoto roomPhoto = new RoomPhoto();
			roomPhoto.setFile(e);
			photos.add(roomPhoto);
		});
		this.setPhotos(photos);
	}
	
	
	
	
	public void setReviews(List<Review> reviews) {
		if (reviews == null) return;
		if (this.reviews == null) {
			this.reviews = new ArrayList<>();
		}
		this.reviews.clear();
		this.reviews.addAll(reviews);
	}
	
	
	
	
	public void setReservations(List<Reservation> reservations) {
		if (reservations == null) return;
		if (this.reservations == null) {
			this.reservations = new ArrayList<>();
		}
		this.reservations.clear();
		this.reservations.addAll(reservations);
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
    	if (t.getCreated()			!= null) this.setCreated(t.getCreated());
    	if (t.getUpdated()			!= null) this.setUpdated(t.getUpdated());
    	this.setCreated(super.getCreated());
    	this.setUpdated(super.getUpdated());
	}
	
	
	
	public double totalRating() {
		return totalRating(this.reviews);
	}
	
	
	
	
	public static double totalRating(List<Review> reviews) {
		if (ValidUtils.isValid(reviews)) {
			double total = 0.0;
			
			for (int i = 0; i < reviews.size(); i++) {
				total += reviews.get(i).ratingAverage();
			}
			
			return Math.round(total * 100 / reviews.size()) / 100.0;
		}
		
		return 0.0;
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
	
	
	@Override
	public String toString() {
		return "Room[id=" + id + ",name=" + name 
				+ ",description=" + description + ",address=" + address 
				+ ",city=" + city + ",country=" + country 
				+ ",price=" + price + ",guests=" + guests
				+ ",beds=" + beds + ",bedrooms=" + bedrooms
				+ ",baths=" + baths + ",checkIn=" + checkIn
				+ ",checkOut=" + checkOut + ",instantBook=" + instantBook
				+ ",host=" + (host != null ? host.getUsername() : null)
				+ ",roomType=" + (roomType != null ? roomType.getName() : null)
				+ ",amenities=" + (amenities != null ? amenities.size() + "amenities" : null)
				+ ",facilities=" + (facilities != null ? facilities.size() + "facilities" : null)
				+ ",houseRules=" + (houseRules != null ? houseRules.size() + "houseRules" : null)
				+ ",photos=" + (photos != null ? photos.size() + "photos" : null)
				+ ",reviews=" + (reviews != null ? reviews.size() + "reviews" : null)
				+ ",reservations=" + (reservations != null ? reservations.size() + "reservations" : null) + "]";
	}
	
}


package com.clone.airbnb.creation.second;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.clone.airbnb.common.Common;
import com.clone.airbnb.entity.Amenity;
import com.clone.airbnb.entity.Facility;
import com.clone.airbnb.entity.HouseRule;
import com.clone.airbnb.entity.Room;
import com.clone.airbnb.entity.RoomPhoto;
import com.clone.airbnb.entity.RoomType;
import com.clone.airbnb.entity.User;
import com.clone.airbnb.repository.AmenityRepository;
import com.clone.airbnb.repository.FacilityRepository;
import com.clone.airbnb.repository.HouseRuleRepository;
import com.clone.airbnb.repository.RoomRepository;
import com.clone.airbnb.repository.RoomTypeRepository;
import com.clone.airbnb.repository.UserRepository;
import com.clone.airbnb.utils.DummyUtils;
import com.clone.airbnb.utils.CollectionUtils;
import com.clone.airbnb.utils.WordUtils;
import com.github.javafaker.Faker;

/**
 * Room Dummy 엔터티를 DB 에 지정한 개수만큼 생성한다.
 */
@SpringBootTest
class RoomCreation {
	
	@Autowired
	private RoomRepository repository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoomTypeRepository roomTypeRepository;
	
	@Autowired
	private FacilityRepository facilityRepository;
	
	@Autowired
	private AmenityRepository amenityRepository;
	
	@Autowired
	private HouseRuleRepository houseRuleRepository;
	
	private final static int ROOM_CREATED = 200;
	
	Faker faker = new Faker(new Locale("en"));
	
	@Test
	void contextLoads() throws ParseException {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, 10);
		Date checkOutEndDate = c.getTime();
		
		List<User> users = CollectionUtils.toList(userRepository.findAll());
		
		List<RoomType> roomTypes = CollectionUtils.toList(roomTypeRepository.findAll());
		
		List<Facility> facilities = CollectionUtils.toList(facilityRepository.findAll());
		
		List<Amenity> amenities = CollectionUtils.toList(amenityRepository.findAll());
		
		List<HouseRule> houseRules = CollectionUtils.toList(houseRuleRepository.findAll());
		
		if (users.isEmpty()) {
			throw new RuntimeException("User가 DB에 존재하지 않습니다. 하나 이상의 User와 RoomType을 먼저 생성하여 주십시오.");
		}
		
		if (roomTypes.isEmpty()) {
			throw new RuntimeException("RoomType이 DB에 존재하지 않습니다. 하나 이상의 User와 RoomType을 먼저 생성하여 주십시오.");
		}
		
		Random random = new Random();
		
		for (int i = 0; i < ROOM_CREATED; i++) {
			User user = DummyUtils.randomCollection(users);
			RoomType roomType = DummyUtils.randomCollection(roomTypes);
			List<Amenity> amenity = DummyUtils.randomMultipleCollection(amenities, 20);
			List<Facility> facility = DummyUtils.randomMultipleCollection(facilities, 6);
			List<HouseRule> houseRule = DummyUtils.randomMultipleCollection(houseRules, 2);
			List<RoomPhoto> photos = new ArrayList<>();
			List<String> fileNames = new ArrayList<>();
			
			for (int j = 0; j < 10;) {
				int index = random.nextInt(30) + 1;
				String originalFilename = index + ".webp";
				
				RoomPhoto photo = new RoomPhoto();
				
				photo.setOriginalFilename(originalFilename);
				photo.setPath(Common.getImagePath("room", originalFilename));
				photo.setUploadPath("#");
				
				if (!fileNames.contains(originalFilename)) {
					photos.add(photo);
					fileNames.add(originalFilename);
					j++;
				}
			}
			
			Room room = new Room();
			room.setName(faker.address().fullAddress());
			room.setDescription(WordUtils.limit(faker.lorem().paragraph(), 100));
			room.setAddress(faker.address().streetName());
			room.setCity(faker.address().city());
			room.setCountry(faker.address().countryCode());
			room.setPrice(faker.number().numberBetween(30, 10000));
			room.setGuests(faker.number().numberBetween(0, 20));
			room.setBeds(faker.number().numberBetween(1, 8));
			room.setBedrooms(faker.number().numberBetween(1, 5));
			room.setBaths(faker.number().numberBetween(1, 3));
			room.setCheckIn(new Date());
			room.setCheckOut(faker.date().between(new Date(), checkOutEndDate));
			room.setInstantBook(faker.bool().bool());
			room.setHost(user);
			room.setRoomType(roomType);
			room.setFacilities(facility);
			room.setAmenities(amenity);
			room.setHouseRules(houseRule);
			room.setPhotos(photos);
			
			repository.save(room);
		}
		
	}
	
}

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
import com.clone.airbnb.dto.SafeUser;
import com.clone.airbnb.entity.Amenity;
import com.clone.airbnb.entity.Facility;
import com.clone.airbnb.entity.HouseRule;
import com.clone.airbnb.entity.Room;
import com.clone.airbnb.entity.RoomType;
import com.clone.airbnb.entity.file.RoomPhoto;
import com.clone.airbnb.repository.AmenityRepository;
import com.clone.airbnb.repository.FacilityRepository;
import com.clone.airbnb.repository.HouseRuleRepository;
import com.clone.airbnb.repository.RoomRepository;
import com.clone.airbnb.repository.RoomTypeRepository;
import com.clone.airbnb.repository.UserRepository;
import com.clone.airbnb.utils.DummyUtils;
import com.clone.airbnb.utils.WordUtils;
import com.github.javafaker.Faker;


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
		
		List<SafeUser> users = new ArrayList<>();
		Iterable<SafeUser> usersIter = userRepository.findAllBy(SafeUser.class);
		usersIter.forEach(users::add);
		
		List<RoomType> roomTypes = new ArrayList<>();
		Iterable<RoomType> roomTypesIter = roomTypeRepository.findAll();
		roomTypesIter.forEach(roomTypes::add);
		
		List<Facility> facilities = new ArrayList<>();
		Iterable<Facility> facilitiesIter = facilityRepository.findAll();
		facilitiesIter.forEach(facilities::add);
		
		List<Amenity> amenities = new ArrayList<>();
		Iterable<Amenity> amenitiesIter = amenityRepository.findAll();
		amenitiesIter.forEach(amenities::add);
		
		List<HouseRule> houseRules = new ArrayList<>();
		Iterable<HouseRule> houseRulesIter = houseRuleRepository.findAll();
		houseRulesIter.forEach(houseRules::add);
		
		if (users.isEmpty()) {
			throw new RuntimeException("User가 DB에 존재하지 않습니다. 하나 이상의 User와 RoomType을 먼저 생성하여 주십시오.");
		}
		
		if (roomTypes.isEmpty()) {
			throw new RuntimeException("RoomType이 DB에 존재하지 않습니다. 하나 이상의 User와 RoomType을 먼저 생성하여 주십시오.");
		}
		
		List<RoomPhoto> photos = new ArrayList<>(); 
		
		for (int i = 1; i <= 31; i++) {
			String originalFilename = i + ".webp";
			String path = Common.getImagePath("room", originalFilename); 
			String uploadPath = "#";
			
			photos.add(RoomPhoto.builder()
					.setOriginalFilename(originalFilename)
					.setPath(path)
					.setUploadPath(uploadPath)
					.build()
					);
		}
		
		Random random = new Random();
		
		for (int i = 0; i < ROOM_CREATED; i++) {
			SafeUser user = DummyUtils.randomCollection(users);
			RoomType roomType = DummyUtils.randomCollection(roomTypes);
			List<Amenity> amenity = DummyUtils.randomMultipleCollection(amenities, 20);
			List<Facility> facility = DummyUtils.randomMultipleCollection(facilities, 6);
			List<HouseRule> houseRule = DummyUtils.randomMultipleCollection(houseRules, 2);
			
			List<Integer> photoIndexes = new ArrayList<>();
			
			for (int j = 0; j < 10;) {
				int index = random.nextInt(31);
				if (photoIndexes.isEmpty() || !photoIndexes.contains(index)) {
					photoIndexes.add(index);
					j++;
				}
			}
			
			List<RoomPhoto> tempPhotos = new ArrayList<>();
			
			for (int j = 0; j < 10; j++) {
				RoomPhoto roomPhoto = photos.get(photoIndexes.get(j));
				tempPhotos.add(roomPhoto);
			}
			
			
			
			Room room = Room.builder()
					.setName(faker.address().fullAddress())
					.setDescription(WordUtils.limit(faker.lorem().paragraph(), 100))
					.setAddress(faker.address().streetName())
					.setCity(faker.address().city())
					.setCountry(faker.address().countryCode())
					.setPrice(faker.number().numberBetween(30, 1000))
					.setGuests(faker.number().numberBetween(0, 20))
					.setBeds(faker.number().numberBetween(0, 8))
					.setBedrooms(faker.number().numberBetween(0, 5))
					.setBaths(faker.number().numberBetween(0, 3))
					.setCheckIn(new Date())
					.setCheckOut(faker.date().between(new Date(), checkOutEndDate))
					.setInstantBook(faker.bool().bool())
					.setHost(user)
					.setRoomType(roomType)
					.setFacilities(facility)
					.setAmenities(amenity)
					.setHouseRules(houseRule)
					.setPhotos(tempPhotos)
					.build();
			
			repository.save(room);
		}
		
	}
	
}

package com.clone.airbnb.creation.third;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.clone.airbnb.dto.SafeUser;
import com.clone.airbnb.entity.Review;
import com.clone.airbnb.entity.Room;
import com.clone.airbnb.repository.ReviewRepository;
import com.clone.airbnb.repository.RoomRepository;
import com.clone.airbnb.repository.UserRepository;
import com.clone.airbnb.utils.DummyUtils;
import com.clone.airbnb.utils.WordUtils;
import com.github.javafaker.Faker;


@SpringBootTest
class ReviewCreation {
	
	@Autowired
	private ReviewRepository repository;
	
	@Autowired
	private RoomRepository roomRepository;
	
	@Autowired
	private UserRepository userRepository;
	

	private final static int REVIEW_CREATED = 1000;
	
	Faker faker = new Faker(new Locale("en"));
	
	@Test
	void contextLoads() throws ParseException {
		
		List<SafeUser> users = new ArrayList<>();
		Iterable<SafeUser> usersIter = userRepository.findAllBy(SafeUser.class);
		usersIter.forEach(users::add);
		
		List<Room> rooms = new ArrayList<>();
		Iterable<Room> roomsIter = roomRepository.findAll();
		roomsIter.forEach(rooms::add);

		
		if (users.isEmpty()) {
			throw new RuntimeException("User가 DB에 존재하지 않습니다. User를 먼저 생성하여 주십시오.");
		}
		
		if (rooms.isEmpty()) {
			throw new RuntimeException("Room이 DB에 존재하지 않습니다. Room을 먼저 생성하여 주십시오.");
		}
		
		
		for (int i = 0; i < REVIEW_CREATED; i++) {
			SafeUser user = DummyUtils.randomCollection(users);
			Room room = DummyUtils.randomCollection(rooms);
			
			Review review = Review.builder()
					.setUser(user)
					.setRoom(room)
					.setReview(WordUtils.limit(faker.lorem().paragraph(), 100))
					.setAccuracy(faker.number().numberBetween(0, 5))
					.setCommunication(faker.number().numberBetween(0, 5))
					.setCleaniness(faker.number().numberBetween(0,5))
					.setLocation(faker.number().numberBetween(0,5))
					.setCheckIn(faker.number().numberBetween(0,5))
					.setValue(faker.number().numberBetween(0,5))
					.build();
			
			repository.save(review);
		}
		
	}
	
}

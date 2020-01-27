package com.clone.airbnb.creation.third;

import java.text.ParseException;
import java.util.List;
import java.util.Locale;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.clone.airbnb.entity.Review;
import com.clone.airbnb.entity.Room;
import com.clone.airbnb.entity.User;
import com.clone.airbnb.repository.ReviewRepository;
import com.clone.airbnb.repository.RoomRepository;
import com.clone.airbnb.repository.UserRepository;
import com.clone.airbnb.utils.DummyUtils;
import com.clone.airbnb.utils.CollectionUtils;
import com.clone.airbnb.utils.WordUtils;
import com.github.javafaker.Faker;

/**
 * Review Dummy 엔터티를 DB 에 지정한 개수만큼 생성한다.
 */
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
		
		List<User> users = CollectionUtils.toList(userRepository.findAll());
		
		List<Room> rooms = CollectionUtils.toList(roomRepository.findAll());
		
		if (users.isEmpty()) {
			throw new RuntimeException("User가 DB에 존재하지 않습니다. User를 먼저 생성하여 주십시오.");
		}
		
		if (rooms.isEmpty()) {
			throw new RuntimeException("Room이 DB에 존재하지 않습니다. Room을 먼저 생성하여 주십시오.");
		}
		
		for (int i = 0; i < REVIEW_CREATED; i++) {
			User user = DummyUtils.randomCollection(users);
			Room room = DummyUtils.randomCollection(rooms);
			
			Review review = new Review();
			review.setUser(user);
			review.setRoom(room);
			review.setReview(WordUtils.limit(faker.lorem().paragraph(), 100));
			review.setAccuracy(faker.number().numberBetween(0, 5));
			review.setCommunication(faker.number().numberBetween(0, 5));
			review.setCleaniness(faker.number().numberBetween(0,5));
			review.setLocation(faker.number().numberBetween(0,5));
			review.setCheckIn(faker.number().numberBetween(0,5));
			review.setValue(faker.number().numberBetween(0,5));
			
			repository.save(review);
		}
		
	}
	
}

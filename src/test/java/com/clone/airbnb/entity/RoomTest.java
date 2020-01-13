package com.clone.airbnb.entity;

import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.clone.airbnb.entity.User;
import com.clone.airbnb.entity.enu.Currency;
import com.clone.airbnb.entity.enu.Gender;
import com.clone.airbnb.entity.enu.Language;
import com.clone.airbnb.formatter.SafeUserByUsernameFormatter;
import com.clone.airbnb.repository.RoomRepository;
import com.clone.airbnb.repository.UserRepository;

@SpringBootTest(properties = "spring.config.location=" 
		+ "classpath:/application-test.yaml")
public class RoomTest {
	
	@Autowired
	RoomRepository roomRepository;
	
	@Autowired
	UserRepository userRepository;
	
	
	private final String name = "한옥 전통 마을";
	private final String description = "한국 전통 한옥 마을";
	private final String address = "강남구";
	private final String city = "서울시";
	private final String country = "KR";
	private final Integer price = 5000;
	private final Integer guests = 5;
	private final Integer beds = 5;
	private final Integer bedrooms = 10;
	private final Integer baths = 3;
	private final Date checkIn = new Date();
	private final Date checkOut = new Date();
	private final Boolean instantBook = false;
	
	
	
	private final String username = "jjw@gmail.com";
	
	
	
	@BeforeEach
	void beforeEach() {
		User host = User.builder()
				.setUsername(username)
				.setPassword("ablksdlkf123")
				.setFirstName("Jin won")
				.setLastName("Jeong")
				.setGender(Gender.OTHER)
				.setLanguage(Language.KOREAN)
				.setCurrency(Currency.KRW)
				.setSuperhost(false)
				.build();
		
		userRepository.save(host);
	}
	
	
	
	@AfterEach
	void afterEach() {
		roomRepository.deleteAll();
		userRepository.deleteAll();
	}
	
	
	
	
	/**
	 * User가 DB에 에러없이 저장되는지 확인한다.
	 * 
	 * @throws ParseException 
	 */
	@Test
	void saveTest() throws ParseException {
		Room room = Room.builder()
				.setName(name)
				.setDescription(description)
				.setAddress(address)
				.setCity(city)
				.setCountry(country)
				.setPrice(price)
				.setGuests(guests)
				.setBeds(beds)
				.setBedrooms(bedrooms)
				.setBaths(baths)
				.setCheckIn(checkIn)
				.setCheckOut(checkOut)
				.setInstantBook(instantBook)
				.setHost(new SafeUserByUsernameFormatter().parse(username, new Locale("")))
				.build();
		
		Room savedRoom = roomRepository.save(room);
		assertNotNull(savedRoom);
		assertNotNull(savedRoom.getId());
	}
	
}

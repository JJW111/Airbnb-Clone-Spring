package com.clone.airbnb.entity.values;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.clone.airbnb.entity.Amenity;
import com.clone.airbnb.entity.Facility;
import com.clone.airbnb.entity.HouseRule;
import com.clone.airbnb.entity.Room;
import com.clone.airbnb.entity.RoomType;
import com.clone.airbnb.entity.User;
import com.clone.airbnb.repository.AmenityRepository;
import com.clone.airbnb.repository.FacilityRepository;
import com.clone.airbnb.repository.HouseRuleRepository;
import com.clone.airbnb.repository.RoomRepository;
import com.clone.airbnb.repository.RoomTypeRepository;
import com.clone.airbnb.repository.UserRepository;
import com.clone.airbnb.utils.CountryUtils;

public class SelectValues {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoomRepository roomRepository;
	
	@Autowired
	private AmenityRepository amenityRepository;
	
	@Autowired
	private FacilityRepository facilityRepository;
	
	@Autowired
	private HouseRuleRepository houseRuleRepository;
	
	@Autowired
	private RoomTypeRepository roomTypeRepository;
	
	
	
	public List<User> users() {
		return userRepository.findAllByOrderByIdDesc();
	}
	
	
	public List<Room> rooms() {
		return roomRepository.findAllByOrderByIdDesc();
	}
	
	
	public List<Amenity> amenities() {
		return amenityRepository.findAllByOrderByIdDesc();
	}
	
	
	public List<Facility> facilities() {
		return facilityRepository.findAllByOrderByIdDesc();
	}
	
	
	public List<HouseRule> houseRules() {
		return houseRuleRepository.findAllByOrderByIdDesc();
	}
	
	
	public List<RoomType> roomTypes() {
		return roomTypeRepository.findAllByOrderByIdDesc();
	}
	
	
	public Map<String, String> countries() {
		return CountryUtils.countries();
	}
	
}

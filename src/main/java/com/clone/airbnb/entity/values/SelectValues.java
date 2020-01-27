package com.clone.airbnb.entity.values;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.clone.airbnb.entity.projection.SelectItem;
import com.clone.airbnb.entity.projection.SelectRoom;
import com.clone.airbnb.entity.projection.SelectUser;
import com.clone.airbnb.entity.projection.impl.SelectItemClass;
import com.clone.airbnb.entity.projection.impl.SelectRoomClass;
import com.clone.airbnb.entity.projection.impl.SelectUserClass;
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
	
	
	
	public List<SelectUserClass> users() {
		List<SelectUser> suList = userRepository.findAllByOrderByIdDesc(SelectUser.class);
		return SelectUserClass.toClasses(suList);
	}
	
	
	public List<SelectRoomClass> rooms() {
		List<SelectRoom> srList = roomRepository.findAllBy(SelectRoom.class);
		return SelectRoomClass.toClasses(srList);
	}
	
	
	public List<SelectItemClass> amenities() {
		List<SelectItem> siList = amenityRepository.findAllBy(SelectItem.class); 
		return SelectItemClass.toClasses(siList);
	}
	
	
	public List<SelectItemClass> facilities() {
		List<SelectItem> siList = facilityRepository.findAllBy(SelectItem.class); 
		return SelectItemClass.toClasses(siList);
	}
	
	
	public List<SelectItemClass> houseRules() {
		List<SelectItem> siList = houseRuleRepository.findAllBy(SelectItem.class); 
		return SelectItemClass.toClasses(siList);
	}
	
	
	public List<SelectItemClass> roomTypes() {
		List<SelectItem> siList = roomTypeRepository.findAllBy(SelectItem.class); 
		return SelectItemClass.toClasses(siList);
	}
	
	
	public Map<String, String> countries() {
		return CountryUtils.countries();
	}
	
}

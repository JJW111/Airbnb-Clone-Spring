package com.clone.airbnb.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.clone.airbnb.dto.RoomUpdateDto;
import com.clone.airbnb.entity.Room;
import com.clone.airbnb.entity.RoomPhoto;
import com.clone.airbnb.entity.projection.RoomDetail;
import com.clone.airbnb.entity.projection.RoomList;
import com.clone.airbnb.entity.projection.RoomPhotos;
import com.clone.airbnb.entity.projection.RoomUpdate;
import com.clone.airbnb.exception.DataDoesNotExistsException;
import com.clone.airbnb.exception.ListSizeOutOfBoundsException;
import com.clone.airbnb.repository.RoomPhotoRepository;
import com.clone.airbnb.repository.RoomRepository;
import com.clone.airbnb.service.RoomService;
import com.clone.airbnb.utils.FileUtils;

@Service
public class RoomServiceImpl implements RoomService {

	@Autowired
	private RoomRepository roomRepository;
	
	@Autowired
	private RoomPhotoRepository roomPhotoRepository;
	
	
	@Override
	public List<Room> all() {
		return roomRepository.findAllByOrderByIdDesc();
	}
	
	
	
	@Override
	public Page<RoomList> rooms(Pageable pageable) {
		return roomRepository.findAllBy(pageable, RoomList.class);
	}
	
	
	
	@Override
	public List<RoomList> rooms(String username) {
		return roomRepository.findAllByHost_username(username, RoomList.class);
	}

	
	
	@Override
	public List<Room> search(String city) {
		return null;
	}
	
	
	
	
	
	@Override
	public void update(RoomUpdateDto dto, String username) {
		Optional<Room> opt = roomRepository.findByIdAndHost_username(dto.getId(), username);
		
		if (opt.isPresent()) {
			Room oldRoom = opt.get();
			oldRoom.setName(dto.getName());
			oldRoom.setDescription(dto.getDescription());
			oldRoom.setAddress(dto.getAddress());
			oldRoom.setCity(dto.getCity());
			oldRoom.setCountry(dto.getCountry());
			oldRoom.setPrice(dto.getPrice());
			oldRoom.setGuests(dto.getGuests());
			oldRoom.setBeds(dto.getBeds());
			oldRoom.setBedrooms(dto.getBedrooms());
			oldRoom.setBaths(dto.getBaths());
			oldRoom.setCheckIn(dto.getCheckIn());
			oldRoom.setCheckOut(dto.getCheckOut());
			oldRoom.setInstantBook(dto.getInstantBook());
			oldRoom.setRoomType(dto.getRoomType());
			oldRoom.setAmenities(dto.getAmenities());
			oldRoom.setFacilities(dto.getFacilities());
			oldRoom.setHouseRules(dto.getHouseRules());
			
			roomRepository.save(oldRoom);
		} else {
			throw new DataDoesNotExistsException("방이 존재하지 않습니다.");
		}
	}
	
	
	
	@Override
	public RoomUpdate getRoomUpdate(Integer id, String username) {
		Optional<RoomUpdate> opt = roomRepository.findByIdAndHost_username(id, username, RoomUpdate.class);
		
		if (opt.isPresent()) {
			return opt.get();
		} else {
			return null;
		}
	}
	
	
	
	@Override
	public RoomDetail getRoomDetail(Integer id) {
		Optional<RoomDetail> opt = roomRepository.findById(id, RoomDetail.class);
		
		if (opt.isPresent()) {
			return opt.get();
		} else {
			return null;
		}
	}
	
	
	
	@Override
	public RoomPhotos getRoomPhotos(Integer roomId, String username) {
		Optional<RoomPhotos> opt = roomRepository.findByIdAndHost_username(roomId, username, RoomPhotos.class);
		
		if (opt.isPresent()) {
			return opt.get();
		} else {
			return null;
		}
	}
	
	
	
	@Override
	public void deletePhoto(Integer roomId, Integer photoId, String username) {
		Long count = roomPhotoRepository.countByRoomIdAndRoom_host_username(roomId, username);
		
		if (count <= 5) {
			throw new ListSizeOutOfBoundsException("Room[" + roomId + "]의 사진 개수가 5개 이하입니다.");
		} else {
			Optional<RoomPhoto> opt = roomPhotoRepository.findByIdAndRoomIdAndRoom_host_username(photoId, roomId, username);
			
			if (opt.isPresent()) {
				RoomPhoto roomPhoto = opt.get();
				FileUtils.delete(roomPhoto.getUploadPath());
				roomPhotoRepository.delete(roomPhoto);
			} else {
				throw new DataDoesNotExistsException("User[" + username + "] - Room[" + roomId 
						+ "] - RoomPhoto[" + photoId + "] 에 해당하는 데이터가 없습니다.");
			}
		}
	}
	
	
	
	@Override
	public void addPhoto(MultipartFile photoFile, Integer roomId, String username) {
		Long count = roomPhotoRepository.countByRoomIdAndRoom_host_username(roomId, username);
		
		if (count >= 10) {
			throw new ListSizeOutOfBoundsException("Room[" + roomId + "]의 사진 개수가 10개 이상입니다.");
		} else {
			RoomPhoto roomPhoto = new RoomPhoto();
			roomPhoto.setFile(photoFile);
			
			Optional<Room> opt = roomRepository.findByIdAndHost_username(roomId, username);
			
			if (opt.isPresent()) {
				FileUtils.save(roomPhoto.getFile(), roomPhoto.getUploadPath());
				Room room = opt.get();
				roomPhoto.setRoom(room);
				room.getPhotos().add(roomPhoto);
				roomRepository.save(room);
			} else {
				throw new DataDoesNotExistsException("User[" + username + "] - Room[" + roomId
						+ "] 에 해당하는 데이터가 없습니다.");
			}
		}
	}
	
}

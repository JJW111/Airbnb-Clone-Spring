package com.clone.airbnb.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.web.multipart.MultipartFile;

import com.clone.airbnb.admin.entity.FileEntity;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "room_photo")
@Getter
@Setter
public class RoomPhoto extends FileEntity {
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "room_id", nullable = false)
	private Room room;
	
	public void setFile(MultipartFile file) {
		super.setFile(file, "room");
	}
	
	@Override
	public String toString() {
		return "RoomPhoto[room=" + (room != null ? room.getId() : null) + ",super=" + super.toString() + "]";
	}
	
}

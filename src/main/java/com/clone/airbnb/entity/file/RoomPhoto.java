package com.clone.airbnb.entity.file;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import com.clone.airbnb.admin.entity.FileEntity;
import com.clone.airbnb.common.Common;
import com.clone.airbnb.entity.Room;
import com.clone.airbnb.utils.FileUtils;
import com.clone.airbnb.utils.ValidUtils;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "room_photo")
@Getter
@Setter(AccessLevel.PRIVATE)
@ToString(exclude = "room", callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(exclude = "room", callSuper = true)
public class RoomPhoto extends FileEntity {
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "room_id", nullable = false)
	private Room room;
	
	
	@ToString(exclude = "file")
	@Getter
	public static class Builder {
		private Integer id;
		private String originalFilename;
    	private String path;
    	private String uploadPath;
		private MultipartFile file;
		private Room room;
		
		
		
		public Builder setId(Integer id) { 
			this.id = id;
			return this;
		}
		
		
		
		public Builder setOriginalFilename(String originalFilename) {
			this.originalFilename = originalFilename;
			return this;
		}
		
		
		
		public Builder setPath(String path) {
			this.path = path;
			return this;
		}
		
		
		
		public Builder setUploadPath(String uploadPath) {
			this.uploadPath = uploadPath;
			return this;
		}
		
		
		
		public Builder setFile(MultipartFile file) {
			this.file = file;
			return this;
		}
		
		
		
		public Builder setRoom(Room room) {
			this.room = room;
			return this;
		}
		
		
		
		public RoomPhoto build() {
			return new RoomPhoto(this);
		}
		
	}
	
	
	
	public static Builder builder() { 
		return new Builder();
	}
	
	
	
	public Builder toBuilder() {
		return builder()
				.setId(this.getId())
				.setFile(this.getFile())
				.setOriginalFilename(this.getOriginalFilename())
				.setPath(this.getPath())
				.setUploadPath(this.getUploadPath())
				.setRoom(this.getRoom());
	}
	
	
	
	private RoomPhoto(Builder builder) {
		this.setId(builder.getId());
		this.setRoom(builder.getRoom());
		if (ValidUtils.isValid(builder.getFile())) {
			this.setFile(builder.getFile());
			this.setOriginalFilename(this.getFile().getOriginalFilename());
			this.setPath(Common.getImagePath("room", FileUtils.randomFileName(FilenameUtils.getExtension(this.getOriginalFilename()))));
			this.setUploadPath(Common.getUploadPath(this.getPath()));
		} else {
			this.setOriginalFilename(builder.getOriginalFilename());
			this.setPath(builder.getPath());
			this.setUploadPath(builder.getUploadPath());
		}
	}
	
}

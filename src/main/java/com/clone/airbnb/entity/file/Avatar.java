package com.clone.airbnb.entity.file;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import com.clone.airbnb.admin.entity.FileEntity;
import com.clone.airbnb.common.Common;
import com.clone.airbnb.entity.User;
import com.clone.airbnb.utils.FileUtils;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "avatar")
@Getter
@ToString(exclude = { "user" }, callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(exclude = "user", callSuper = true)
public class Avatar extends FileEntity {
	
	@OneToOne(mappedBy = "avatar")
    private User user;
	
	
	
	@ToString(exclude = "file")
	@Getter
	public static class Builder {
		private Integer id;
		private String originalFilename;
    	private String path;
		private MultipartFile file;
		
		
		
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
		
		
		
		public Builder setFile(MultipartFile file) {
			this.file = file;
			return this;
		}
		
		
		
		public Avatar build() {
			return new Avatar(this);
		}
		
	}
	
	
	
	public static Builder builder() { 
		return new Builder();
	}
	
	
	
	private Avatar(Builder builder) {
		this.setId(builder.getId());
		if (builder.file != null) {
			this.setFile(builder.file);
			this.setOriginalFilename(FilenameUtils.getName(builder.getFile().getOriginalFilename()));
			this.setPathWithUploadPath(Common.getImagePath("avatar", FileUtils.randomFileName(FilenameUtils.getExtension(this.getOriginalFilename()))));
		} else {
			this.setOriginalFilename(builder.getOriginalFilename());
			this.setPathWithUploadPath(builder.getPath());
		}
	}
	
	
	
	public void setPathWithUploadPath(String path) {
		super.setPath(path);
		super.setUploadPath(Common.getUploadPath(this.getPath()));
	}
	
}

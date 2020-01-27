package com.clone.airbnb.entity;

import javax.persistence.Entity;

import org.springframework.web.multipart.MultipartFile;

import com.clone.airbnb.admin.entity.FileEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "avatar")
@Getter
@Setter
@ToString(callSuper = true)
public class Avatar extends FileEntity {
	public void setFile(MultipartFile file) {
		super.setFile(file, "avatar");
	}
}

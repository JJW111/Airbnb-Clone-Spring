package com.clone.airbnb.admin.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import com.clone.airbnb.common.Common;
import com.clone.airbnb.utils.FileUtils;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = { "file" })
public class FileEntity implements FileEntityFrame {
	
	@Id
    @GeneratedValue
    private Integer id;
	
	
	
	@Column(nullable = false)
	private String originalFilename;
	
	
	
	@Column(nullable = false)
	private String path;
	
	
	
	@Column(nullable = false)
	protected String uploadPath;
	
	
	
	@Transient
	private MultipartFile file;
	
	
	
	protected void setFile(MultipartFile file, String sub) {
		if (file != null) {
			this.file = file;
			this.originalFilename = file.getOriginalFilename();
			this.path = Common.getImagePath(sub, FileUtils.randomFileName(FilenameUtils.getExtension(originalFilename)));
			this.uploadPath = Common.getUploadPath(this.path);
		}
	}
	
	
	
	@Override
	public boolean equals(Object obj) {
		FileEntity f = (FileEntity) obj;
		if (this.getId() == null || f.getId() == null) return false;
		if (!this.getId().equals(f.getId())) return false;
		return true;
	}
	
}

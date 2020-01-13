package com.clone.airbnb.admin.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@MappedSuperclass
@Getter
@Setter(AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = { "file" })
@EqualsAndHashCode(of = { "id" })
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
	
	
	
	
	public void setFile(MultipartFile file) {
		if (file != null) {
			this.file = file;
			this.originalFilename = file.getOriginalFilename();
		}
	}
	
}

package com.clone.airbnb.utils;

import java.util.Collection;

import org.springframework.web.multipart.MultipartFile;

import com.clone.airbnb.admin.entity.FileEntity;

public class ValidUtils {
	
	public static boolean isValid(MultipartFile file) {
		return file != null && !file.isEmpty();
	}
	
	
	public static boolean isValid(String str) {
		return str != null && !str.isEmpty();
	}
	
	
	public static boolean isValid(Collection<?> list) {
		return list != null && !list.isEmpty();
	}
	
	public static boolean isValid(FileEntity f) {
		return f.getOriginalFilename() != null && f.getPath() != null && f.getUploadPath() != null;
	}
	
}

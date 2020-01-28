package com.clone.airbnb.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import com.clone.airbnb.admin.entity.FileEntity;
import com.clone.airbnb.exception.FileStorageException;


public class FileUtils {

	public static void save(FileEntity fileEntity) {
		if (fileEntity != null) {
			FileUtils.save(fileEntity.getFile(), fileEntity.getUploadPath());
		}
	}
	
	
	public static void save(MultipartFile multipartFile, String absolutePath) {
		if (multipartFile == null || absolutePath == null) return; 
			
		String strDir = FilenameUtils.getPath(absolutePath);
		
		File dir = new File(strDir);
		
		if (!dir.exists()) {
			if (!dir.mkdir()) {
				throw new FileStorageException("Directory 를 생성하는데 실패하였습니다: " + strDir);
			}
		}
		
		try {
			Path copyLocation = Paths
	                .get(absolutePath);
			Files.copy(multipartFile.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			throw new FileStorageException("File: " + absolutePath + " 업로드 중 예외 발생", e);
		}
	}
	
	
	public static String randomFileName(String extension) {
		return UUID.randomUUID().toString() + "." + extension;
	}
	
	
	public static void delete(String absolutePath) {
		if (absolutePath == null) return;
		
		File file = new File(absolutePath);
		
		if (file.exists()) {
			if (!file.delete()) {
				throw new FileStorageException("File: " + absolutePath + " 삭제 실패!");
			}
		}
	}
	
}

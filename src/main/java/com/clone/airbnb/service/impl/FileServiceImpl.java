package com.clone.airbnb.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.clone.airbnb.admin.entity.FileEntity;
import com.clone.airbnb.service.FileService;
import com.clone.airbnb.utils.FileUtils;

@Service
public class FileServiceImpl implements FileService {

	@Override
	public void save(FileEntity e) {
		MultipartFile file = e.getFile();
		if (file != null) {
			String uploadPath = e.getUploadPath();
			if (uploadPath != null) {
				FileUtils.save(file, uploadPath);
			}
		}
	}

}

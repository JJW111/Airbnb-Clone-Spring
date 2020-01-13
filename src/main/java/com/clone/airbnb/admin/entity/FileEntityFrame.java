package com.clone.airbnb.admin.entity;

import org.springframework.web.multipart.MultipartFile;

public interface FileEntityFrame extends FileProjectionFrame {
	MultipartFile getFile();
}

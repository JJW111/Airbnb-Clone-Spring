package com.clone.airbnb.common;

public class Common {
	public static final boolean PRODUCT_ENV = false;
	public static final String STATIC_ROOT = PRODUCT_ENV ? "" : "src/main/resources/static";
	public static final String IMAGE_FOLDER = "image";
	public static final String IMAGE_ROOT = PRODUCT_ENV ? "" : STATIC_ROOT + "/" + IMAGE_FOLDER;
	
	
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	
	public static String getImagePath(String sub, String fileName) {
		return "/" + IMAGE_FOLDER + "/" + sub + "/" + fileName;
	}
	
	public static String getUploadPath(String path) {
		if (path == null) return null;
		else return STATIC_ROOT + path;
	}
}

package com.clone.airbnb.utils;

import java.util.Collection;

public final class CommonUtils {
	
	public static int iterableSize(Iterable<?> data) {
		return ((Collection<?>) data).size();
	}
	
	public static String emptyCheck(String str) {
		if (str == null) return null;
		return str.trim().isEmpty() ? null : str;
	}
	
}

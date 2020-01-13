package com.clone.airbnb.utils;

import java.util.Collection;

public final class CommonUtils {
	
	public static int iterableSize(Iterable<?> data) {
		return ((Collection<?>) data).size();
	}
	
	public static String emptyCheck(String str) {
		return str.trim().isEmpty() ? null : str;
	}
	
}

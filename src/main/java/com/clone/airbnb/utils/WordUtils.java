package com.clone.airbnb.utils;

public final class WordUtils {
	
	
	/**
	 * <p>변수 name 을 출력하기 적절한 이름으로 변환한다.
	 * 
	 * <ul>이름을 바꾸는 공식
	 * 	<li>첫 문자는 대문자로
	 * 	<li>대문자는 띄어쓰기를 추가한다.
	 * 
	 * @param name
	 */
	public static String toAlias(String name) {
		String alias = "";
		
		if (name == null || name.length() == 0) return null;
		
		alias += name.substring(0, 1).toUpperCase();
		
		for (int i = 1; i < name.length(); i++) {
			char ch = name.charAt(i);
			
			if (Character.isUpperCase(ch)) {
				alias += " " + ch;
			} else if (ch == '_') {
				alias += " ";
			} else {
				alias += ch;
			}
		}
		
		return alias;
	}
	
	
	
	/**
	 * 문자열 str의 첫 문자를 대문자로 변환 후 반환한다.
	 * 
	 * @param str 변환할 문자열
	 * @return 첫 문자를 대문자로 변환한 문자열
	 */
	public static String capitalize(String str) {
		return str == null ? null : str.substring(0, 1).toUpperCase() + str.substring(1);
	}
	
	
	
	
	public static String limit(String str, int max) {
		if (str == null) return null;
		
		if (str.length() > max) {
			return str.substring(0, max);
		} else {
			return str;
		}
	}
	
}

package com.clone.airbnb.utils;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CountryUtils {

	private static class Singleton {
		private static final Map<String, String> countries;
		
		static {
			String[] locales = Locale.getISOCountries();
			Map<String, String> map = new HashMap<>();
			
			for (String countryCode : locales) {
				Locale obj = new Locale("", countryCode);
				map.put(obj.getCountry(), obj.getDisplayCountry(new Locale("en")));
			}
			
			countries = map;
		}
	}
	
	public static Map<String, String> countries() {
		return Singleton.countries;
	}
	
}



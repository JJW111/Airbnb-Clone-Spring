package com.clone.airbnb.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class DummyUtils {

	private static final Random random = new Random();
	
	
	
	@SuppressWarnings("unchecked")
	public static <T extends Enum<T>> T randomEnum(Class<T> enu) {
		Object[] values = (Object[]) ReflectionInvocator.invoke(enu, "values");
		int randomIndex = (int) (Math.random() * values.length);
		return (T) values[randomIndex];
	}
	
	

	/**
	 * <p>랜덤으로 true 혹은 false 값을 반환한다.
	 * 
	 * <p>trueWeight(0 <= x < 1.0) 값이 클수록 true 값을 반환할 확률이 커진다.
	 * 
	 * @param trueWeight
	 * @return
	 * @throws IllegalArgumentException trueWeight의 값이 0보다 작거나 1보다 같거나 크면
	 */
	public static boolean randomBoolean(double trueWeight) {
		if (trueWeight < 0) throw new IllegalArgumentException("trueWeight의 값이 0보다 작습니다. 허용된 범위(0 <= x < 1)");
		if (trueWeight >= 1) throw new IllegalArgumentException("trueWeight의 값이 1보다 같거나 큽니다. 허용된 범위(0 <= x < 1)");
		if (Math.random() > trueWeight) {
			return false;
		} else {
			return true;
		}
	}
	
	
	
	
	/**
	 * <p>랜덤으로 true 혹은 false 값을 반환한다.
	 * 
	 * @return boolean
	 */
	public static boolean randomBoolean() {
		return randomBoolean(0.5);
	}
	
	
	
	
	public static <T> T randomIterable(Iterable<T> iter) {
		if (iter == null || !iter.iterator().hasNext()) return null;
		
		Collection<T> collection = new ArrayList<>();
		iter.forEach(collection::add);
		
		return randomCollection(collection);
	}
	
	
	
	public static <T> List<T> randomMultipleIterable(Iterable<T> iter, int max) {
		if (iter == null || !iter.iterator().hasNext()) return null;
		
		List<T> list = new ArrayList<>();
		iter.forEach(list::add);
		
		return randomMultipleCollection(list, max);
	}
	
	
	
	public static <T> T randomCollection(Collection<T> collection) {
		if (collection == null || collection.isEmpty()) {
			return null;
		} else {
			@SuppressWarnings("unchecked")
			T[] array = (T[]) collection.toArray();
			return randomArray(array);
		}
	}
	
	
	
	public static <T> List<T> randomMultipleCollection(Collection<T> collection, int max) {
		List<T> list = new ArrayList<>();

		for (int i = 0; i < random.nextInt(max); i++) {
			list.add(randomCollection(collection));
		}
		
		return list;
	}
	
	
	public static <T> T randomArray(T[] array) {
		if (array == null || array.length == 0) {
			return null;
		} else {
			return array[random.nextInt(array.length)];
		}
	}
	
	
	public static <T> Set<T> randomMultipleArray(T[] array, int max) {
		Set<T> set = new HashSet<>();

		for (int i = 0; i < random.nextInt(max); i++) {
			set.add(randomArray(array));
		}
		
		return set;
	}
	
}

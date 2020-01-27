package com.clone.airbnb.utils;

import java.util.ArrayList;
import java.util.List;

public class CollectionUtils {

	public static <T> List<T> toList(Iterable<T> iter) {
		if (iter == null) return null;
		List<T> list = new ArrayList<>();
		iter.forEach(list::add);
		return list;
	}
	
	public static <T> List<T> toEntityList(CollectionStrategyStatement<T> stat) {
		List<T> list = new ArrayList<>();
		return stat.execute(list);
	}
	
}

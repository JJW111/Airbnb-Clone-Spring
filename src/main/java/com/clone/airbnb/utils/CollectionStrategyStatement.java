package com.clone.airbnb.utils;

import java.util.List;

public interface CollectionStrategyStatement<T> {
	List<T> execute(final List<T> list);
}

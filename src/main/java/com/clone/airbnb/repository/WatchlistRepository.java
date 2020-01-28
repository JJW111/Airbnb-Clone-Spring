package com.clone.airbnb.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import com.clone.airbnb.annotation.EntityMapping;
import com.clone.airbnb.entity.Watchlist;

@EntityMapping(entity=Watchlist.class)
public interface WatchlistRepository extends PagingAndSortingRepository<Watchlist, Integer> {
}


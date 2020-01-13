package com.clone.airbnb.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.clone.airbnb.annotation.EntityMapping;
import com.clone.airbnb.entity.Watchlist;

@EntityMapping(entity=Watchlist.class)
public interface WatchlistRepository extends PagingAndSortingRepository<Watchlist, Integer> {
	
	Page<Watchlist> findByOrderByIdAsc(Pageable pageable);
	
	Page<Watchlist> findByOrderByIdDesc(Pageable pageable);
	
}


package com.clone.airbnb.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.clone.airbnb.annotation.EntityMapping;
import com.clone.airbnb.entity.Message;

@EntityMapping(entity=Message.class)
public interface MessageRepository extends PagingAndSortingRepository<Message, Integer> {
	
	Page<Message> findByOrderByIdAsc(Pageable pageable);
	
	Page<Message> findByOrderByIdDesc(Pageable pageable);
	
}


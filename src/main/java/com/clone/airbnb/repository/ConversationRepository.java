package com.clone.airbnb.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.clone.airbnb.annotation.EntityMapping;
import com.clone.airbnb.entity.Conversation;

@EntityMapping(entity=Conversation.class)
public interface ConversationRepository extends PagingAndSortingRepository<Conversation, Integer> {
	boolean existsByIdAndParticipants_id(Integer id, Integer participantId);
	<T> Optional<T> findById(Integer id, Class<T> clazz);
	<T> List<T> findAllBy(Class<T> clazz);
	<T> Page<T> findAllBy(Pageable pageable, Class<T> clazz); 
}


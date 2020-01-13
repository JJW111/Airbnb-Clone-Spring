package com.clone.airbnb.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.clone.airbnb.annotation.EntityMapping;
import com.clone.airbnb.entity.Conversation;

@EntityMapping(entity=Conversation.class)
public interface ConversationRepository extends PagingAndSortingRepository<Conversation, Integer> {
	
	Page<Conversation> findByOrderByIdAsc(Pageable pageable);
	
	Page<Conversation> findByOrderByIdDesc(Pageable pageable);
	
	Conversation findByIdAndParticipants_id(Integer id, Integer participantId);
	
}


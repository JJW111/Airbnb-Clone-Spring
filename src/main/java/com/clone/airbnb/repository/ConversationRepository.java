package com.clone.airbnb.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import com.clone.airbnb.annotation.EntityMapping;
import com.clone.airbnb.entity.Conversation;

@EntityMapping(entity=Conversation.class)
public interface ConversationRepository extends PagingAndSortingRepository<Conversation, Integer> {
	boolean existsByIdAndParticipants_id(Integer id, Integer participantId);
}


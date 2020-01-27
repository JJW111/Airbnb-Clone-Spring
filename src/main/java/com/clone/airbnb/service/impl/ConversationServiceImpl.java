package com.clone.airbnb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clone.airbnb.repository.ConversationRepository;
import com.clone.airbnb.service.ConversationService;

@Service
public class ConversationServiceImpl implements ConversationService {

	@Autowired
	private ConversationRepository conversationRepository;
	
	@Override
	public boolean isParticipant(int conversationId, int participantId) {
		return conversationRepository.existsByIdAndParticipants_id(conversationId, participantId);
	}

}

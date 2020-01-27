package com.clone.airbnb.entity.projection.admin;

import java.util.List;

import com.clone.airbnb.entity.projection.EmptyMessage;
import com.clone.airbnb.entity.projection.SelectUser;

public interface ConversationProjection {
	Integer getId();
	List<SelectUser> getParticipants();
	List<EmptyMessage> getMessages();
	
	default int countParticipants() {
		if (getParticipants() == null) return 0;
		return getParticipants().size();
	}
	
	default int countMessages() {
		if (getMessages() == null) return 0;
		return getMessages().size();
	}
}

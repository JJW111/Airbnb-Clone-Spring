package com.clone.airbnb.entity.projection.admin;

import com.clone.airbnb.entity.projection.ConversationOnlyId;
import com.clone.airbnb.entity.projection.SelectUser;

public interface MessageProjection {
	Integer getId();
	String getMessage();
	SelectUser getUser();
	ConversationOnlyId getConversation();
}

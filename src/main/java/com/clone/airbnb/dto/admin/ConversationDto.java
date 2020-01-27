package com.clone.airbnb.dto.admin;

import java.util.List;

import com.clone.airbnb.entity.Conversation;
import com.clone.airbnb.entity.User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ConversationDto implements DtoToOriginalSwitcher<Conversation> {
	private List<User> participants;
	
	@Override
    public Conversation toOriginal() {
		Conversation conversation = new Conversation();
		conversation.setParticipants(this.getParticipants());
		
		return conversation;
    }
}

package com.clone.airbnb.dto.admin;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.clone.airbnb.entity.Conversation;
import com.clone.airbnb.entity.Message;
import com.clone.airbnb.entity.User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MessageDto {
    @Size(min = 1, max = 100, message = "100자 까지 작성해주세요.")
	private String message;
	@NotNull(message = "존재하지 않는 유저입니다.")
	private User user;
	@NotNull(message = "선택한 대화방이 존재하지 않습니다.")
	private Conversation conversation;
	
    public Message toOriginal() {
    	Message message = new Message();
    	message.setMessage(this.getMessage());
    	message.setUser(this.getUser());
    	message.setConversation(this.getConversation());
    	
		return message;
    }
}

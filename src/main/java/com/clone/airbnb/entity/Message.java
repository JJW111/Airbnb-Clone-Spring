package com.clone.airbnb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.clone.airbnb.admin.entity.AdminFormEntity;
import com.clone.airbnb.admin.form.annotation.EntityForm;
import com.clone.airbnb.admin.form.annotation.JoinOneForm;
import com.clone.airbnb.admin.form.annotation.JoinOneTextForm;
import com.clone.airbnb.admin.form.annotation.TextForm;

import lombok.Getter;
import lombok.Setter;

@EntityForm
@Entity
@Table(name = "message")
@Getter
@Setter
public class Message implements AdminFormEntity<Message> {
	
	
	@Id
    @GeneratedValue
    private Integer id;
	
	
	
	@TextForm(maxlength = 100)
	@Column(nullable = false)
    @Size(max = 100, message = "100자 까지 작성해주세요.")
	private String message;
	
	
	
	
	@JoinOneForm(blank = false, itemLabel = "username", itemValue = "id", method = "users", defaultOption = "------ Select User ------")
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, unique =  false)
	@NotNull(message = "존재하지 않는 유저입니다.")
	private User user;
	
	
	
	
	@JoinOneTextForm(blank = false, field = "id", placeholder = "------ Conversation ID ------")
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "conversation_id", referencedColumnName="id", nullable = false, unique = false)
	@NotNull(message = "선택한 대화방이 존재하지 않습니다.")
	private Conversation conversation;
	
	
	
	
	@Override
	public void override(Message t) {
		if (t.getId()				!= null) this.setId(t.getId());
		if (t.getMessage()			!= null) this.setMessage(t.getMessage());
		if (t.getUser()				!= null) this.setUser(t.getUser());
		if (t.getConversation()		!= null) this.setConversation(t.getConversation());
	}
	
	
	
	@Override
	public String toString() {
		Integer conversationId = conversation != null ? conversation.getId() : null;
		return "Message[id=" + id + ",message=" + message 
				+ ",user=" + (user != null ? user.getUsername() : null) 
				+ ",conversation=" + conversationId + "]";
	}
	
}

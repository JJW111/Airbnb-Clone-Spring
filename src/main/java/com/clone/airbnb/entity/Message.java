package com.clone.airbnb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import com.clone.airbnb.admin.entity.AdminFormEntity;
import com.clone.airbnb.admin.form.annotation.EntityForm;
import com.clone.airbnb.admin.form.annotation.JoinOneForm;
import com.clone.airbnb.admin.form.annotation.TextForm;
import com.clone.airbnb.dto.SafeUser;
import com.clone.airbnb.repository.ConversationRepository;
import com.clone.airbnb.repository.UserRepository;
import com.clone.airbnb.utils.BeanUtils;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EntityForm
@Entity
@Table(name = "message")
@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Message implements AdminFormEntity<Message>{
	
	
	@Id
    @GeneratedValue
    private Integer id;
	
	
	
	@TextForm(maxlength = 100)
	@Column(nullable = false)
	@Length(min = 1, max = 100)
	private String message;
	
	
	
	
	@JoinOneForm(blank = false, field = "username", repository = UserRepository.class, defaultOption = "------ Select User ------")
	@OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, unique =  false)
	@NotNull(message = "해당 대화방에 참가한 유저를 선택하여 주십시오.")
	private User user;
	
	
	
	
	@JoinOneForm(blank = false, field = "id", repository = ConversationRepository.class, defaultOption = "------ Select Conversation ------")
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "conversation_id", referencedColumnName="id", nullable = false, unique = false)
	private Conversation conversation;
	
	
	
	
	@Override
	public String toString() {
		String username = user != null ? user.getUsername() : null;
		Integer conversationId = conversation != null ? conversation.getId() : null;
		return "Message[id=" + id + ",message=" + message + ",user=" + username + ",conversation=" + conversationId + "]";
	}
	
	
	
	
	@Getter
	public static class Builder {
	    private Integer id;
	    @NotBlank
	    @Size(max = 100, message = "100자 까지 작성해주세요.")
	    private String message;
	    @NotNull(message = "존재하지 않는 유저입니다.")
	    private SafeUser user;
	    @NotNull(message = "선택한 대화방이 존재하지 않습니다.")
	    private Conversation conversation;
	    
	    
	    
	    public Builder setId(Integer id) {
	    	this.id = id;
	    	return this;
	    }
	    
	    
	    
	    public Builder setMessage(String message) {
	    	this.message = message;
	    	return this;
	    }
	    
	    
	    
	    public Builder setUser(SafeUser user) {
	    	this.user = user;
	    	return this;
	    }
	    
	    
	    
	    public Builder setConversation(Conversation conversation) {
	    	this.conversation = conversation;
	    	return this;
	    }
	    
	    
	    
	    public Message build() {
	    	return new Message(this);
	    }
	    
	    
	    
	    @Override
	    public String toString() {
	    	return this.build().toString();
	    }
	    
	    
	}
	
	
	
	public static Builder builder() {
		return new Builder();
	}
	
	
	
	private Message(Builder builder) {
		this.setId(builder.getId());
		this.setMessage(builder.getMessage());
		if (builder.getUser() != null && builder.getConversation() != null) {
			Conversation conversation = ((ConversationRepository) BeanUtils.getBean(ConversationRepository.class))
					.findByIdAndParticipants_id(builder.getConversation().getId(), builder.getUser().getId());
			
			if (conversation != null) {
				this.setUser(User.toUser(builder.getUser()));
			}
		}
		this.setConversation(builder.getConversation());
	}
	


	@Override
	public Message deepClone() {
		Message message = builder()
				.setId(this.getId())
				.setMessage(this.getMessage())
				.setConversation(this.getConversation())
				.build();
		message.setUser(this.getUser());
		
		return message;
	}



	@Override
	public void override(Message t) {
		if (t.getId()				!= null) this.setId(t.getId());
		if (t.getMessage()			!= null) this.setMessage(t.getMessage());
		if (t.getUser()				!= null) this.setUser(t.getUser());
		if (t.getConversation()		!= null) this.setConversation(t.getConversation());
	}
	
}

package com.clone.airbnb.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.clone.airbnb.admin.entity.AdminFormEntity;
import com.clone.airbnb.admin.form.annotation.EntityForm;
import com.clone.airbnb.admin.form.annotation.JoinManyForm;
import com.clone.airbnb.dto.SafeUser;
import com.clone.airbnb.repository.UserRepository;
import com.clone.airbnb.utils.ValidUtils;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EntityForm
@Entity
@Table(name = "conversation")
@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Conversation implements AdminFormEntity<Conversation>{
	
	
	@Id
    @GeneratedValue
    private Integer id;
	
	
	
	@JoinManyForm(field = "username", defaultOption = "----- Select Users -----", repository = UserRepository.class)
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			  name = "conversation_user",
			  joinColumns = @JoinColumn(name = "conversation_id"),
			  inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<User> participants;
	
	
	
	
	@OneToMany(mappedBy = "conversation", orphanRemoval = true)
	private List<Message> messages;

	
	
	
	public List<Integer> participants() {
		if (this.participants == null) return null;
		
		List<Integer> ids = new ArrayList<>();
		
		for (User u : participants) {
			ids.add(u.getId());
		}
		
		return ids;
	}
	
	
	
	
	public List<Integer> messages() {
		if (this.messages == null) return null;
		
		List<Integer> ids = new ArrayList<>();
		
		for (Message m : messages) {
			ids.add(m.getId());
		}
		
		return ids;
	}
	
	
	
	public int countParticipants() {
		if (participants == null) return 0;
		return participants.size();
	}
	
	
	
	public int countMessages() {
		if (messages == null) return 0;
		return messages.size();
	}
	
	
	
	@Override
	public String toString() {
		return "Conversation[id=" + id + ",participants=" + participants() +
				",messages=" + messages() + ",countParticipants=" + countParticipants() + ",countMessages=" + countMessages() + "]";
	}
	
	
	
	@Getter
	public static class Builder {
	    private Integer id;
		private List<SafeUser> participants;
	    private List<Message> messages;
	    
	    
	    
	    public Builder setId(Integer id) {
	    	this.id = id;
	    	return this;
	    }
	    
	    
	    
	    public Builder setParticipants(List<SafeUser> participants) {
	    	this.participants = participants;
	    	return this;
	    }
	    
	    
	    
	    
	    public Builder setMessages(List<Message> messages) {
	    	this.messages = messages;
	    	return this;
	    }
	    
	    
	    
	    
	    public Conversation build() {
	    	return new Conversation(this);
	    }
	    
	    
	    
	    @Override
	    public String toString() {
	    	return this.build().toString();
	    }
	    
	}
	
	
	
	
	private Conversation(Builder builder) {
		this.setId(builder.getId());
		this.setParticipants(User.toUsers(builder.getParticipants()));
		this.setMessages(builder.getMessages());
	}
	
	
	
	
	public static Builder builder() {
		return new Builder();
	}
	
	
	
	public Builder toBuilder() {
		final List<SafeUser> safeUsers = new ArrayList<>();
		
		if (ValidUtils.isValid(this.getParticipants())) {
			this.getParticipants().forEach(e -> {
				if (e != null) {
					safeUsers.add(e.toSafeUser());
				}
			});
		}
		
		return builder()
				.setId(this.getId())
				.setMessages(this.getMessages())
				.setParticipants(safeUsers);
	}
	
	
	
	
	@Override
	public Conversation deepClone() {
		return this.toBuilder().build();
	}
	



	@Override
	public void override(Conversation t) {
		if (t.getId()				!= null) this.setId(t.getId());
		if (t.getParticipants()		!= null) this.setParticipants(t.getParticipants());
		if (t.getMessages()			!= null) this.setMessages(t.getMessages());
	}
	
}

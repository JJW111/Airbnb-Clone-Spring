package com.clone.airbnb.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
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

import lombok.Getter;
import lombok.Setter;

@EntityForm
@Entity
@Table(name = "conversation")
@Getter
@Setter
public class Conversation implements AdminFormEntity<Conversation> {
	
	
	@Id
    @GeneratedValue
    private Integer id;
	
	
	
	@JoinManyForm(itemLabel = "username", itemValue = "id", method = "users", defaultOption = "----- Select Participants -----")
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
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
	public void override(Conversation t) {
		if (t.getId()				!= null) this.setId(t.getId());
		if (t.getParticipants()		!= null) this.setParticipants(t.getParticipants());
		if (t.getMessages()			!= null) this.setMessages(t.getMessages());
	}
	
	
	
	@Override
	public String toString() {
		return "Conversation[id=" + id + ",participants=" + participants() 
			+ ",messages=" + messages() + ",countParticipants=" + countParticipants() 
			+ ",countMessages=" + countMessages() + "]";
	}

}

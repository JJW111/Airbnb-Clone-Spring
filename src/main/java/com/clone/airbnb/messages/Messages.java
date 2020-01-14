package com.clone.airbnb.messages;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Messages {
	private List<Message> messages;
	
	@ToString
	public static class Builder {
		private List<Message> messages = new ArrayList<>();
		
		public Builder add(Message m) {
			this.messages.add(m);
			return this;
		}
		
		public Builder add(String m) {
			this.messages.add(new Message(m));
			return this;
		}
		
		public Builder add(String m, Tags tags) {
			this.messages.add(new Message(m, tags));
			return this;
		}
		
		public Messages build() {
			return new Messages(this);
		}
	}
	
	public static Builder builder() {
		return new Builder();
	}
	
	private Messages(Builder builder) {
		this.messages = builder.messages;
	}
	
}

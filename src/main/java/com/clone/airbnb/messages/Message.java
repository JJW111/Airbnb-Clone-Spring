package com.clone.airbnb.messages;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Message {
	private String text;
	private Tags tags = Tags.ERROR;
	
	public Message(String text) {
		this.text = text;
	}
	
	public Message(String text, Tags tags) {
		this(text);
		this.tags = tags;
	}
}

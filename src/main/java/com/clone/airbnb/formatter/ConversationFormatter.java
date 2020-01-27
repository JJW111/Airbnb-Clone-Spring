package com.clone.airbnb.formatter;

import java.text.ParseException;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import com.clone.airbnb.entity.Conversation;
import com.clone.airbnb.repository.ConversationRepository;

@Component
public class ConversationFormatter implements Formatter<Conversation>{

	@Autowired
	private ConversationRepository conversationRepository;
	
	@Override
	public String print(Conversation object, Locale locale) {
		return object.toString();
	}

	@Override
	public Conversation parse(String text, Locale locale) throws ParseException {
		if (text == null) return null;
		
		Optional<Conversation> opt = conversationRepository.findById(Integer.parseInt(text));
		
		if (opt.isPresent()) {
			return opt.get();
		} else {
			return null;
		}
	}
	
}

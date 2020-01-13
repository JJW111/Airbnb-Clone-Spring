package com.clone.airbnb.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

import com.clone.airbnb.entity.Conversation;
import com.clone.airbnb.repository.ConversationRepository;
import com.clone.airbnb.utils.BeanUtils;

public class ConversationFormatter implements Formatter<Conversation>{

	@Override
	public String print(Conversation object, Locale locale) {
		return object.toString();
	}

	@Override
	public Conversation parse(String text, Locale locale) throws ParseException {
		return ((ConversationRepository) BeanUtils.getBean(ConversationRepository.class)).findById(Integer.parseInt(text)).get();
	}
	
}

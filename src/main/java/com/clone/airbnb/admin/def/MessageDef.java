package com.clone.airbnb.admin.def;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebRequestDataBinder;

import com.clone.airbnb.admin.annotation.AdminEntityDefinition;
import com.clone.airbnb.admin.schema.AdminEntityConfiguration;
import com.clone.airbnb.admin.schema.vo.FieldList;
import com.clone.airbnb.admin.schema.vo.FieldSet;
import com.clone.airbnb.entity.Message;
import com.clone.airbnb.formatter.ConversationFormatter;
import com.clone.airbnb.formatter.NullFormatter;
import com.clone.airbnb.formatter.UserFormatter;
import com.clone.airbnb.utils.ValidatorUtils;

@Component
@AdminEntityDefinition(entity=Message.class)
public class MessageDef implements AdminEntityConfiguration {

	@Autowired
	private UserFormatter userFormatter;
	
	@Autowired
	private ConversationFormatter conversationFormatter;
	
	@Autowired
	private NullFormatter nullFormatter;
	
	
	
	@Override
	public String group() {
		return "Conversations";
	}
	
	
	
	@Override
	public FieldList fieldList() {
		return FieldList.builder()
				.field("id")
				.field("user", "Username")
				.field("message")
				.field("conversation", "Conversation ID")
				.build();
	}
	
	
	
	@Override
	public FieldSet fieldSet() {
		FieldSet fieldSet = new FieldSet.Builder()
				.set("Message.")
					.field("message")
				.set("Select.")
					.field("user")
					.field("conversation")
				.build();
		
		return fieldSet;
	}
	
	
	
	@Override
	public void initWebRequestDataBinder(WebRequestDataBinder binder) {
		if (binder == null) {
			throw new NullPointerException("WebRequestDataBinder 가 null 입니다.");
		}
		
		binder.addCustomFormatter(userFormatter, "user");
		binder.addCustomFormatter(conversationFormatter, "conversation");
		binder.addCustomFormatter(nullFormatter);
		binder.setValidator(ValidatorUtils.getValidator());
	}
	
}

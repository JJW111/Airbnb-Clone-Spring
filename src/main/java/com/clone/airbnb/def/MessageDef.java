package com.clone.airbnb.def;

import org.springframework.web.bind.support.WebRequestDataBinder;

import com.clone.airbnb.admin.annotation.AdminEntityDefinition;
import com.clone.airbnb.admin.schema.AdminEntityConfiguration;
import com.clone.airbnb.admin.schema.vo.FieldList;
import com.clone.airbnb.admin.schema.vo.FieldSet;
import com.clone.airbnb.entity.Message;
import com.clone.airbnb.formatter.ConversationFormatter;
import com.clone.airbnb.formatter.NullFormatter;
import com.clone.airbnb.formatter.SafeUserFormatter;
import com.clone.airbnb.utils.ValidatorUtils;

@AdminEntityDefinition(entity=Message.class)
public class MessageDef implements AdminEntityConfiguration {

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
		
		binder.addCustomFormatter(new SafeUserFormatter(), "user");
		binder.addCustomFormatter(new ConversationFormatter(), "conversation");
		binder.addCustomFormatter(new NullFormatter());
		binder.setValidator(ValidatorUtils.getValidator());
	}
	
}

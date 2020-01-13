package com.clone.airbnb.def;

import org.springframework.web.bind.support.WebRequestDataBinder;

import com.clone.airbnb.admin.annotation.AdminEntityDefinition;
import com.clone.airbnb.admin.schema.AdminEntityConfiguration;
import com.clone.airbnb.admin.schema.vo.FieldList;
import com.clone.airbnb.admin.schema.vo.FieldSet;
import com.clone.airbnb.entity.Conversation;
import com.clone.airbnb.formatter.SafeUsersFormatter;
import com.clone.airbnb.utils.ValidatorUtils;

@AdminEntityDefinition(entity=Conversation.class)
public class ConversationDef implements AdminEntityConfiguration {

	@Override
	public String group() {
		return "Conversations";
	}
	
	
	
	@Override
	public FieldList fieldList() {
		return FieldList.builder()
				.field("id")
				.field("countParticipants", "Participants. Cnt")
				.field("countMessages", "Messages. Cnt")
				.build();
	}
	
	
	
	@Override
	public FieldSet fieldSet() {
		FieldSet fieldSet = new FieldSet.Builder()
				.set("Conversations")
					.field("participants")
				.build();
		
		return fieldSet;
	}
	
	
	
	@Override
	public void initWebRequestDataBinder(WebRequestDataBinder binder) {
		if (binder == null) {
			throw new NullPointerException("WebRequestDataBinder 가 null 입니다.");
		}
		
		binder.addCustomFormatter(new SafeUsersFormatter(), "participants");
		binder.setValidator(ValidatorUtils.getValidator());
	}
	
}

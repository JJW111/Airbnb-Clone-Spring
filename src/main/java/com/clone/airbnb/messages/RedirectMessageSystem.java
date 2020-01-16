package com.clone.airbnb.messages;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;


public class RedirectMessageSystem {
	
	private final static String attr = "messages";
	
	public static class Builder {
		private RedirectAttributes redirectAttr;
		private Messages.Builder messages = Messages.builder();
		
		public Builder add(String text) {
			messages.add(new Message(text));
			return this;
		}
		
		public Builder add(String text, Tags tags) {
			messages.add(new Message(text, tags));
			return this;
		}
		
		public void build() {
			new RedirectMessageSystem(this);
		}
		
		public Builder(RedirectAttributes redirectAttr) {
			this.redirectAttr = redirectAttr;
		}
	}
	
	public static Builder builder(RedirectAttributes redirectAttr) {
		return new Builder(redirectAttr);
	}
	
	private RedirectMessageSystem(Builder builder) {
		builder.redirectAttr
			.addFlashAttribute(attr, builder.messages.build());
	}
	
}

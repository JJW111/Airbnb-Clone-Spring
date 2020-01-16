package com.clone.airbnb.messages;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;


public class RedirectMessageSystem {
	
	private final static String attr = "messages";
	
	public static class Builder {
		private RedirectAttributes redirectAttr;
		private Messages.Builder messages = Messages.builder();
		
		public Builder error(String text) {
			messages.add(new Message(text, Tags.ERROR));
			return this;
		}
		
		public Builder debug(String text) {
			messages.add(new Message(text, Tags.DEBUG));
			return this;
		}
		
		public Builder info(String text) {
			messages.add(new Message(text, Tags.INFO));
			return this;
		}
		
		public Builder warning(String text) {
			messages.add(new Message(text, Tags.WARNING));
			return this;
		}
		
		public Builder success(String text) {
			messages.add(new Message(text, Tags.SUCCESS));
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

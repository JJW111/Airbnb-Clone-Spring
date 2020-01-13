package com.clone.airbnb.service;


public interface MailService {
	void sendMail(String to, String subject, String text);
}

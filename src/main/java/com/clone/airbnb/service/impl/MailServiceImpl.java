package com.clone.airbnb.service.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.clone.airbnb.service.MailService;

@Service
public class MailServiceImpl implements MailService {

	@Autowired
	private JavaMailSender mailSender;
	
	@Override
	public void sendMail(String to, String subject, String text) {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
		
		try {
			helper.setFrom("noreply@sandbox6e1464ae15d449709e17450f4b581c4e.mailgun.org");
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(text, true);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		
		mailSender.send(mimeMessage);
	}

}

package com.example;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class MailService {

	@Autowired
	JavaMailSender mailSender;

	// Simple Email
	public boolean send(String to, String subject, String text) {
		return send(to, null, null, subject, text, null);
	}

	// Email + 1 Attachment
	public boolean send(String to, String subject, String text, Resource file) {
		return send(to, null, null, subject, text, new Resource[] { file });
	}

	// Email with all Attachments
	public boolean send(String to, String[] cc, String[] bcc, String subject, String text, Resource[] files) {

		boolean sent = false;

		try {

			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, (files != null && files.length > 0));
			helper.setTo(to);
			if (cc != null && cc.length > 0)
				helper.setCc(cc);
			if (bcc != null && bcc.length > 0)
				helper.setBcc(bcc);
			helper.setSubject(subject);
			helper.setText(text, true);

			if (files != null && files.length > 0) {
				for (Resource file : files)
					helper.addAttachment(file.getFilename(), file);
			}
			mailSender.send(message);
			sent = true;

		} catch (Exception e) {
			e.printStackTrace();
			sent = false;
		}

		return sent;
	}

}

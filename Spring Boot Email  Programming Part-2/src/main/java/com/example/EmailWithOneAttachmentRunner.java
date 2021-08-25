package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

@Component
public class EmailWithOneAttachmentRunner implements CommandLineRunner {

	@Autowired
	MailService service;

	@Override
	public void run(String... args) throws Exception {

		String message = "<html><body><h1>Hello User</h1><b>this is bold text</b></body></html>";

		// email with one attachments
		boolean sent = service.send("krishna.sk7991@gmail.com", "Mail from Spring Boot App", message, new FileSystemResource("D:\\India-v-England-1.jpg"));
		if (sent)
			System.out.println("EmailWithOneAttachmentRunner :: SENT");
		else
			System.out.println("EmailWithOneAttachmentRunner :: NOT SENT");

	}

}

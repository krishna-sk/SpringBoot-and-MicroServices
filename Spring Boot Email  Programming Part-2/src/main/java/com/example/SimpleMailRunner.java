package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SimpleMailRunner implements CommandLineRunner {

	@Autowired
	MailService service;

	@Override
	public void run(String... args) throws Exception {

		String message = "<html><body><h1>Hello User</h1><b>this is bold text</b></body></html>";

		// simple email
		boolean sent = service.send("krishna.sk7991@gmail.com", "Mail from Spring Boot App", message);
		if (sent)
			System.out.println("SimpleMailRunner :: SENT");
		else
			System.out.println("SimpleMailRunner :: NOT SENT");

	}

}

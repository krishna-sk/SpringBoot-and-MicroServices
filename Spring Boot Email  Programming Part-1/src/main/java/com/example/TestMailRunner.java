package com.example;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class TestMailRunner implements CommandLineRunner {

	@Autowired
	private MyMailService service;

	Resource file = new FileSystemResource("D:/spring-boot.png");
	@Override
	public void run(String... args) throws Exception {

		boolean sent = service.send("krishna.sk7991@gmail.com", new String[] {"krishna.sk7991@outlook.in"},
				new String[] {"sbmsbackup@gmail.com"}, "SUBJECT", "Message"+new Date(), file);

		
		if(sent)
			System.out.println("SENT");
		else
			System.out.println("NOT SENT");
	}

}

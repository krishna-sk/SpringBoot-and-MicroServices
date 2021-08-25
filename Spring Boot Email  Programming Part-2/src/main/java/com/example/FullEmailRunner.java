package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;

@Component
public class FullEmailRunner implements CommandLineRunner {

	@Autowired
	MailService service;

	@Override
	public void run(String... args) throws Exception {

		String message = "<html><body><h1>Hello User</h1><b>this is bold text</b></body></html>";
		Resource[] files = new Resource[] { new FileSystemResource("D:\\spring-boot.png"),
				new FileSystemResource("D:\\India-v-England-1.jpg"),
				new UrlResource("https://c.ndtvimg.com/2020-05/tkqluj48_virat-kohli-afp_625x300_30_May_20.jpg"),
				new ClassPathResource("hellodata.txt")};

		// email with all attachments
		boolean sent = service.send("krishna.sk7991@gmail.com", new String[] { "sbmsbackup@gmail.com" },
				new String[] { "amazonprimebyus@gmail.com" }, "Mail from Spring Boot App", message, files);
		if (sent)
			System.out.println("FullEmailRunner :: SENT");
		else
			System.out.println("FullEmailRunner :: NOT SENT");

	}

}

package com.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
@Profile("qa")
public class QARunner implements CommandLineRunner {
	
	@Value("${my.title}")
	private String title;

	@Override
	public void run(String... args) throws Exception {
		
		System.out.println("QA Data is inserted :: "+title);
		
	}

}

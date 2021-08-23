package com.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class Runner implements CommandLineRunner {
	
	@Value("${my.title}")
	private String title;

	@Override
	public void run(String... args) throws Exception {
		
		System.out.println("Runner Data is inserted :: "+title);
		
	}

}

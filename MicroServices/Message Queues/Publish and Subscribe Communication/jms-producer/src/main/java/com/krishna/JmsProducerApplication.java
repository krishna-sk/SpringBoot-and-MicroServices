package com.krishna;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class JmsProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(JmsProducerApplication.class, args);
	}

}

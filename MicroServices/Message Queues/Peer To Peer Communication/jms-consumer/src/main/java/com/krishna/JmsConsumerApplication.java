package com.krishna;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class JmsConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(JmsConsumerApplication.class, args);
	}

}

package com.krishna.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class ProducerService {

	@Value("${my.app.desti-name}")
	private String destination;

	@Autowired
	private JmsTemplate jmsTemplate;

	public void sendMessage(String message) {
		jmsTemplate.send(destination, session -> session.createTextMessage(message));
	}

}

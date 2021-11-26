package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ConsumerService {

	@Autowired
	private MessageStoreService messageStoreService;

	@KafkaListener(topics = "${my.app.tpc-name}")
	public void readData(String message) {
		log.info("Message at Consumer {}", message);
		messageStoreService.save(message);
	}
}
package com.krishna.runner;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.krishna.service.ProducerService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TestProducerRunner {

	@Autowired
	private ProducerService service;

	@Scheduled(fixedDelay = 5000)
	public void run() throws Exception {
		service.sendMessage("HELLO SAMPLE " + new Date());
		log.info("MESSAGE IS SENT!!!");
	}

}
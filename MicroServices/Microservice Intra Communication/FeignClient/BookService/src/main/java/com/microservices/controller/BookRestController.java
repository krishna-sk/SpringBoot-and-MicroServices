package com.microservices.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.consumer.AuthorConsumer;

@RestController
@RequestMapping("/book")
public class BookRestController {

	@Autowired
	private AuthorConsumer consumer;
	
	@GetMapping("/print")
	public ResponseEntity<String> printMsg() {
         	System.out.println(consumer.getClass().getName());
		String body = consumer.showData().getBody();
		return ResponseEntity.ok("FROM BOOK ===>" + body);
				
	}
}
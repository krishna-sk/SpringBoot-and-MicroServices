package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Quote;
import com.example.service.MessageStoreService;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {

	@Autowired
	private MessageStoreService messageStoreService;

	@GetMapping("/all")
	public ResponseEntity<List<Quote>> fetchAll() {
		List<Quote> list = messageStoreService.getAll();
		return ResponseEntity.ok(list);
	}

}

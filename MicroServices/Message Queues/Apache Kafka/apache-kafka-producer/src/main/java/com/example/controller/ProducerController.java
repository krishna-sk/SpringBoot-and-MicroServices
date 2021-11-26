package com.example.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Quote;
import com.example.service.ProducerService;
import com.example.util.JsonUtil;

@RestController
@RequestMapping("/producer")
public class ProducerController {

	@Autowired
	private ProducerService producerService;

	@Autowired
	private JsonUtil jsonUtil;

	@GetMapping("/create/{code}/{cost}")
	public ResponseEntity<String> createQuote(@PathVariable String code, @PathVariable Double cost) {

		// create obj
		Quote q = new Quote();
		q.setCode(code);
		q.setCost(cost);
		q.setDate(new Date());

		// convert to JSON
		String json = jsonUtil.toJson(q);

		// call producer
		producerService.sendQuote(json);

		return ResponseEntity.ok("Created!!");
	}

}
package com.microservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.consumer.VendorConsumer;

@RestController
@RequestMapping("/product")
public class ProductRestController {

	@Autowired
	private VendorConsumer consumer;

	@GetMapping("/info")
	public ResponseEntity<String> showInfo() {
		String resp = consumer.getVendorData();
		return new ResponseEntity<String>("FROM PRODUCT ==>" + resp, HttpStatus.OK);
	}
}
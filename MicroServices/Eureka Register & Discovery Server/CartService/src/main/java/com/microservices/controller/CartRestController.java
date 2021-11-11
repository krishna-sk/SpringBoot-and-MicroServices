package com.microservices.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartRestController {
	
	@GetMapping("/data")
	public ResponseEntity<String> showMessage() {
		return new ResponseEntity<>("HELLO CART!",HttpStatus.OK);
	}
}
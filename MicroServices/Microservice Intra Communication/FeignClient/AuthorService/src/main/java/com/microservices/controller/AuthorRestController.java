package com.microservices.controller;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/author")
public class AuthorRestController {

	@Value("${server.port}")
	private String port;
	
	@GetMapping("/data")
	public ResponseEntity<String> showData() {
		return new ResponseEntity<String>("HELLO AUTHOR :: " + port, HttpStatus.OK);
	}
}
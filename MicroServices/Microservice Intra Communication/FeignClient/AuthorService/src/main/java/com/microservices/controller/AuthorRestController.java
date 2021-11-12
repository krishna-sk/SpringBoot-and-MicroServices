package com.microservices.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.entity.Author;

@RestController
@RequestMapping("/author")
public class AuthorRestController {

	@Value("${server.port}")
	private String port;
	
	@GetMapping("/data")
	public ResponseEntity<String> showData() {
		return new ResponseEntity<String>("HELLO AUTHOR :: " + port, HttpStatus.OK);
	}

	@GetMapping("/find/{id}")
	public ResponseEntity<Author> getOne(@PathVariable Integer id) {
		// return ResponseEntity.ok(new Author(id, "DUMMY"));
		return new ResponseEntity<Author>(new Author(id, "DUMMY"), HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<String> createAuthor(@RequestBody Author author) {
		return ResponseEntity.ok("Author created => " + author.getAuthId() + " & port : "+ port);
	}
}
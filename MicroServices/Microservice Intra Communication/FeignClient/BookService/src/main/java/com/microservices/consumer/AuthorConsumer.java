package com.microservices.consumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.microservices.entity.Author;

@FeignClient("AUTHOR-SERVICE")
public interface AuthorConsumer {

	@GetMapping("/author/data")
	public ResponseEntity<String> showData();

	@GetMapping("/author/find/{id}")
	public ResponseEntity<Author> getOne(@PathVariable Integer id);

	@PostMapping("/author/create")
	public ResponseEntity<String> createAuthor(@RequestBody Author author);
}
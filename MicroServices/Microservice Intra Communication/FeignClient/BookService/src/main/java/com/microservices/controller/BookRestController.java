package com.microservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.consumer.AuthorConsumer;
import com.microservices.entity.Author;
import com.microservices.entity.Book;

@RestController
@RequestMapping("/book")
public class BookRestController {

	@Autowired
	private AuthorConsumer consumer;

	@GetMapping("/print")
	public ResponseEntity<String> printMsg() {
		String body = consumer.showData().getBody();
		return ResponseEntity.ok("FROM BOOK ===>" + body);

	}

	@GetMapping("/fetch/{bid}/{aid}")
	public ResponseEntity<Book> findData(@PathVariable Integer bid, @PathVariable Integer aid) {
		ResponseEntity<Author> resp = consumer.getOne(aid);
		return ResponseEntity.ok(new Book(bid, "ABC", resp.getBody()));
	}

	@PostMapping("/save")
	public ResponseEntity<String> createBook(@RequestBody Book book) {
		ResponseEntity<String> resp = consumer.createAuthor(book.getAuth());
		String msg = "Book created! => " + book.getBookId();
		return ResponseEntity.ok(msg + " & " + resp.getBody());
	}
}
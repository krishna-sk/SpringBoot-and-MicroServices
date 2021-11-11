package com.microservices.consumer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("AUTHOR-SERVICE")
public interface AuthorConsumer {

	@GetMapping("/author/data")
	public ResponseEntity<String> showData();
}
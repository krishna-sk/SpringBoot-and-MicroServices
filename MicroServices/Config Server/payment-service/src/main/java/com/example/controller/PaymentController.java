package com.example.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pay")
@RefreshScope
public class PaymentController {

	@Value("${my.app.title}")
	private String title;

	@GetMapping("/data")
	public ResponseEntity<String> showTitle() {
		return ResponseEntity.ok("Title is " + title);
	}

}

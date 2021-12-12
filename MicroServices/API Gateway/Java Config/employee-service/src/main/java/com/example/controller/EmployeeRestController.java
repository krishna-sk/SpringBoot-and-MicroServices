package com.example.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeRestController {

	@Value("${server.port}")
	private String port;

	@GetMapping("/msg")
	public ResponseEntity<String> showData(@RequestHeader("sample-request-employee-header") String input) {
		return ResponseEntity.ok("HELLO from Employee Module => " + port+" ,"+input);
	}
}
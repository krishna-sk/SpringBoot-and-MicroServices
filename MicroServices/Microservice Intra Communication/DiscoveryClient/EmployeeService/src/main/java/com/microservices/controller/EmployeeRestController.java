package com.microservices.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.consumer.DepartmentConsumer;

@RestController
@RequestMapping("/emp")
public class EmployeeRestController {

	@Autowired
	private DepartmentConsumer consumer;
	
	@GetMapping("/info")
	public ResponseEntity<String> showInfo() {
		String body = consumer.getDeptData();
		return new ResponseEntity<String>("FROM EMP ==> " + body, HttpStatus.OK);
	}
}
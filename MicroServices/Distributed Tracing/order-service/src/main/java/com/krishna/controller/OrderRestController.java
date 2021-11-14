package com.krishna.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderRestController {

	// private static final Logger log = LoggerFactory.getLogger(OrderRestController.class);

	@GetMapping("/data")
	public String authenticate() {
		log.info("Entered into to ORDER SERVICE !!!");
		return "Order Rest Controller !!!";
	}
}

package com.krishna.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/authentication")
@Slf4j
public class AuthenticationRestController {

	@Autowired
	private RestTemplate restTemplate;

	// private static final Logger log = LoggerFactory.getLogger(AuthenticationRestController.class);

	@GetMapping("/data")
	public String authenticate() {
		log.info("Entered into to AUTHENTICATION SERVICE !!!");
		String response = restTemplate.getForObject("http://localhost:8082/user/data", String.class);
		log.info("Back to AUTHENTICATION SERVICE, Data is {}", response);
		return "Authentication Rest Controller !!!";
	}
}

package com.krishna;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserRestController {

	@Autowired
	private RestTemplate restTemplate;

	// private static final Logger log = LoggerFactory.getLogger(UserRestController.class);

	@GetMapping("/data")
	public String showUser() {
		log.info("Entered into to USER SERVICE !!!");
		String response = restTemplate.getForObject("http://localhost:8083/order/data", String.class);
		log.info("Back to USER SERVICE, Data is {}", response);
		return "User Rest Controller !!!";
	}
}

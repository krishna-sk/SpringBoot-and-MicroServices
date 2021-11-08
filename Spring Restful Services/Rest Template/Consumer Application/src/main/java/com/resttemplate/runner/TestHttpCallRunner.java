package com.resttemplate.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.resttemplate.entity.Employee;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TestHttpCallRunner implements CommandLineRunner {

	public void run(String... args) throws Exception {

		getRequestWithString();

		getRequestWithPathVaribale();

		getRequestWithObject();

		postRequest();

		getRequestWithExchangeMethod();

		postRequestWithExchangeMethod();

		putRequestWithExchangeMethod();

		deleteRequestWithExchangeMethod();

		// Stop server
		System.exit(0);

	}

	private void getRequestWithString() {
		// 1. Create RestTemplate class object
		RestTemplate rt = new RestTemplate();

		// 2. Create URL for Producer
		String url = "http://localhost:8080/employee/msg";

		// 3. Make Request CALL and get Response
		// URL, ResponseType, PathVariables(Object...)
		ResponseEntity<String> response = rt.getForEntity(url, String.class);

		// 4. Print Response details
		log.info("Response Body     : {}", response.getBody());
		log.info("Response code-val : {}", response.getStatusCodeValue());
		log.info("Response code-name: {}", response.getStatusCode().name());
		log.info("Response Headers  : {}", response.getHeaders());

	}

	private void getRequestWithPathVaribale() {
		// 1. Create RestTemplate class object
		RestTemplate rt = new RestTemplate();

		// 2. Create URL for Producer
		String url = "http://localhost:8080/employee/msg/{id}";

		// 3. Make Request CALL and get Response
		// URL, ResponseType, PathVariables(Object...)
		ResponseEntity<String> response = rt.getForEntity(url, String.class, 990);

		// 4. Print Response details
		log.info("Response Body     : {}", response.getBody());
		log.info("Response code-val : {}", response.getStatusCodeValue());
		log.info("Response code-name: {}", response.getStatusCode().name());
		log.info("Response Headers  : {}", response.getHeaders());

	}

	private void getRequestWithObject() {
		// 1. Create RestTemplate class object
		RestTemplate rt = new RestTemplate();

		// 2. Create URL for Producer
		String url = "http://localhost:8080/employee/findOne";

		// 3. Make Request CALL and get Response
		// URL, ResponseType, PathVariables(Object...)
		ResponseEntity<Employee> response = rt.getForEntity(url, Employee.class);

		// 4. Print Response details
		log.info("Response Body     : {}", response.getBody());
		log.info("Response code-val : {}", response.getStatusCodeValue());
		log.info("Response code-name: {}", response.getStatusCode().name());
		log.info("Response Headers  : {}", response.getHeaders());
	}

	private void postRequest() {
		// 1. Create RestTemplate class object
		RestTemplate rt = new RestTemplate();

		// 2. Create URL for Producer
		String url = "http://localhost:8080/employee/create";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		String body = "{\"id\":101,\"name\":\"SAM\",\"salary\":250.0}";

		HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);

		// 3. Make Request CALL and get Response
		ResponseEntity<String> response = rt.postForEntity(url, requestEntity, String.class);

		// 4. Print Response details
		log.info("Response Body     : {}", response.getBody());
		log.info("Response code-val : {}", response.getStatusCodeValue());
		log.info("Response code-name: {}", response.getStatusCode().name());
		log.info("Response Headers  : {}", response.getHeaders());
	}

	private void getRequestWithExchangeMethod() {
		// 1. Create RestTemplate class object
		RestTemplate rt = new RestTemplate();

		// 2. Create URL for Producer
		String url = "http://localhost:8080/employee/msg/{id}";

		// 3. Make Request CALL and get Response
		// URL, Http Method, RequestEntity, ResponseType, PathVariables
		ResponseEntity<String> response = rt.exchange(url, HttpMethod.GET, null, String.class, 360);

		// 4. Print Response details
		log.info("Response Body     : {}", response.getBody());
		log.info("Response code-val : {}", response.getStatusCodeValue());
		log.info("Response code-name: {}", response.getStatusCode().name());
		log.info("Response Headers  : {}", response.getHeaders());
	}

	private void postRequestWithExchangeMethod() {

		// 1. Create RestTemplate class object
		RestTemplate rt = new RestTemplate();

		// 2. Create URL for Producer
		String url = "http://localhost:8080/employee/create";

		// __________________________________________________________
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		String body = "{\"id\":101,\"name\":\"SAM\",\"salary\":250.0}";

		HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);

		// __________________________________________________________

		// 3. Make Request CALL and get Response
		// URL, HttpMethod, requestEntity, ResponseType, pathVariables
		ResponseEntity<String> response = rt.exchange(url, HttpMethod.POST, requestEntity, String.class);

		// 4. Print Response details
		log.info("Response Body     : {}", response.getBody());
		log.info("Response code-val : {}", response.getStatusCodeValue());
		log.info("Response code-name: {}", response.getStatusCode().name());
		log.info("Response Headers  : {}", response.getHeaders());

	}

	private void putRequestWithExchangeMethod() {

		// 1. Create RestTemplate class object
		RestTemplate rt = new RestTemplate();

		// 2. Create URL for Producer
		String url = "http://localhost:8080/employee/update";

		// __________________________________________________________
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		String body = "{\"id\":101,\"name\":\"SAM\",\"salary\":250.0}";

		HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);

		// __________________________________________________________

		// 3. Make Request CALL and get Response
		// URL, HttpMethod, requestEntity, ResponseType, pathVariables
		ResponseEntity<String> response = rt.exchange(url, HttpMethod.PUT, requestEntity, String.class);

		// 4. Print Response details
		log.info("Response Body     : {}", response.getBody());
		log.info("Response code-val : {}", response.getStatusCodeValue());
		log.info("Response code-name: {}", response.getStatusCode().name());
		log.info("Response Headers  : {}", response.getHeaders());

	}

	private void deleteRequestWithExchangeMethod() {

		// 1. Create RestTemplate class object
		RestTemplate rt = new RestTemplate();

		// 2. Create URL for Producer
		String url = "http://localhost:8080/employee/remove/{id}";

		// 3. Make Request CALL and get Response
		// URL, Http Method, RequestEntity, ResponseType, PathVariables
		ResponseEntity<String> response = rt.exchange(url, HttpMethod.DELETE, null, String.class, 995);

		// 4. Print Response details
		log.info("Response Body     : {}", response.getBody());
		log.info("Response code-val : {}", response.getStatusCodeValue());
		log.info("Response code-name: {}", response.getStatusCode().name());
		log.info("Response Headers  : {}", response.getHeaders());

	}

}

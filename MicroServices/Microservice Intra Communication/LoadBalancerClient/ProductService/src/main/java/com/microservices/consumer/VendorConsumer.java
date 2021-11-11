package com.microservices.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class VendorConsumer {

	@Autowired
	private LoadBalancerClient client;

	public String getVendorData() {
		// goto eureka to get SI
		ServiceInstance si = client.choose("VENDOR-SERVICE");

		// read URI and add path
		String url = si.getUri() + "/vendor/data";

		// Rest Template and make call
		RestTemplate rt = new RestTemplate();
		ResponseEntity<String> resp = rt.getForEntity(url, String.class);

		// return response
		return resp.getBody();
	}
}
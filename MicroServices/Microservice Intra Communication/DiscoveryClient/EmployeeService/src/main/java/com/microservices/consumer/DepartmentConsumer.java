package com.microservices.consumer;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class DepartmentConsumer {

	@Autowired
	private DiscoveryClient client;
	
	public String getDeptData() {
		//Goto Eureka to fetch List<SI>
		List<ServiceInstance> list = client.getInstances("DEPT-SERVICE");

		//Read SI from index#0
		//SI = SID + IID + URI(HOST + PORT) + LF
		ServiceInstance si = list.get(0);
		
		//Read URI from SI 
		URI uri = si.getUri();
		
		//Create URL by adding Path
		String url = uri + "/dept/data";
		
		//now use RestTemplate and make HTTP call
		RestTemplate rt = new RestTemplate();
		ResponseEntity<String>  response =  rt.getForEntity(url, String.class);

		//return response body
		return response.getBody();
	}
	
}
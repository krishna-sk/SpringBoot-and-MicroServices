package com.example.demo.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class Employee {
	
	@Value("${employee.id}")
	private String id;
	
	@Value("${employee.name}")
	private String name;
	
	@Value("${employee.dept}")
	private String dept;
	
	@Value("${employee.fee}")
	private String fee;
}

package com.example.demo.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class Student {

	@Value("${student.id}")
	private Integer id;
	
	@Value("${student.name}")
	private String name;
	
}

package com.springboot.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Student {
	
	@Value("${my.id}")
	private Integer id;
	
	@Value("${my.name}")
	private String name;
	
	@Value("${my.dept}")
	private String dept;
	
	@Value("${my.own.key1}")
	private String temp1;
	
	@Value("${my.own.key2}")
	private String temp2;
	
	@Value("${my.own.key3}")
	private String temp3;

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", dept=" + dept + ", temp1=" + temp1 + ", temp2=" + temp2
				+ ", temp3=" + temp3 + "]";
	}

	
	
}

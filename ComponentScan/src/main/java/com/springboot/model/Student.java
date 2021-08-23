package com.springboot.model;

import org.springframework.stereotype.Component;

@Component
public class Student {

	private Integer Id;

	private String name;
	
	public Student() {
		super();
	}

	public Student(Integer id, String name) {
		super();
		Id = id;
		this.name = name;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Student [Id=" + Id + ", name=" + name + "]";
	}
	

}

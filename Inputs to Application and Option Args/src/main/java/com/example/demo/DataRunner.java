package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.model.Student;

@Component
public class DataRunner implements CommandLineRunner {

	@Autowired
	Student student;
	
	@Override
	public void run(String... args) throws Exception {
		
		System.out.println(student);
	}

}

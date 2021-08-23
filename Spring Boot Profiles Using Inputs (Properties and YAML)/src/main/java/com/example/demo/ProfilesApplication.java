package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.example.demo.model.Employee;
import com.example.demo.model.Student;

@SpringBootApplication
public class ProfilesApplication {

	public static void main(String[] args) {
		ApplicationContext ctx =  SpringApplication.run(ProfilesApplication.class, args);
		
		Student student = ctx.getBean("student",Student.class);
		
		Employee user = ctx.getBean("employee",Employee.class);
		System.out.println("\n");
		System.out.println(student);
		System.out.println("\n");
		System.out.println(user);
	}

}

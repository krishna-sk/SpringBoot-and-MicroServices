package com.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;

import com.springboot.model.Student;

@SpringBootApplication
@PropertySource("classpath:custom.properties")
public class Application {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Application.class, args);
		
		Student student = context.getBean("student",Student.class);
		
		System.out.println(student);
	}

}

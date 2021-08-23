package com.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class TaskApplication {

	public static void main(String[] args) {
		ApplicationContext ctx=  SpringApplication.run(TaskApplication.class, args);
		Object student = ctx.getBean("student");
		System.out.println(student);
	}

}

package com.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.springboot.model.Student;

import in.app.test.Employee;

@SpringBootApplication
@ComponentScan({"com.springboot","in.app.test"})
public class Application {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Application.class, args);

		Student student = ctx.getBean("student", Student.class);
		student.setId(1);
		student.setName("sai");
		System.out.println(student);

		Employee employee = ctx.getBean("employee", Employee.class);
		employee.setId(2);
		employee.setName("krishna");
		System.out.println(employee);

	}

}

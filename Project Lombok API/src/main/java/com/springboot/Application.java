package com.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.springboot.model.Employee;
import com.springboot.model.Student;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Application.class, args);
		
		Student student = context.getBean("student",Student.class);
		student.setId(123);
		student.setName("Sai");
		student.setDept("Statistics");
		
		Employee employee = context.getBean("employee",Employee.class);
		employee.setId(456);
		employee.setName("Krishna");
		employee.setDept("Software");
		
		System.out.println(student);
		System.out.println(employee);
	}

}

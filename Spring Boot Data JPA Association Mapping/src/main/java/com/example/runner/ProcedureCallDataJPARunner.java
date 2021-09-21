package com.example.runner;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.entity.Employee;
import com.example.repository.EmployeeRepository;

@Component
public class ProcedureCallDataJPARunner implements CommandLineRunner {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public void run(String... args) throws Exception {

		System.out.println("\n\n + ProcedureCallDataJPARunner + \n\n");

		Integer count = employeeRepository.getEmployeesCountByDesg("DEV");
		System.out.println("\n\n\n");
		System.out.println(count);

		List<Employee> allEmployees = employeeRepository.getAllEmployees();
		System.out.println("\n\n\n");
		allEmployees.forEach(System.out::println);

		List<Employee> allEmployees1 = employeeRepository.getAllEmployeesByDept("DEV");
		System.out.println("\n\n\n");
		allEmployees1.forEach(System.out::println);

	}

}

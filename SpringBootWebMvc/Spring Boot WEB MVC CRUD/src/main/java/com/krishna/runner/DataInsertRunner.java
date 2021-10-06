package com.krishna.runner;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.krishna.entity.Employee;
import com.krishna.repository.EmployeeRepository;

@Component
public class DataInsertRunner implements CommandLineRunner {

	@Autowired
	EmployeeRepository employeeRepository;

	private Employee createdEmployee;

	private String[] employeeValues;

	private double salary;

	private Date doj;

	@Override
	public void run(String... args) throws Exception {

		List<Employee> listOfEmployees = new ArrayList<Employee>();

		InputStream resource = new ClassPathResource("MOCK_DATA.csv").getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(resource));

		String line = br.readLine(); // to skip first line, we will read next line again.
		line = br.readLine();

		while (line != null) {
			employeeValues = line.split(",");
			createdEmployee = createEmployee(employeeValues);
			listOfEmployees.add(createdEmployee);
			line = br.readLine();
		}
		employeeRepository.saveAll(listOfEmployees);
	}

	private Employee createEmployee(String[] values) {
		salary = Double.parseDouble(values[4]) * 100;
		doj = Date.valueOf(values[6]);
		return Employee.builder().name(values[1]).gender(values[2]).email(values[3]).salary(salary)
				.department(values[5]).hra((salary * 12) / 100.0).ta((salary * 3) / 100.0).doj(doj)
				.build();
	}

}

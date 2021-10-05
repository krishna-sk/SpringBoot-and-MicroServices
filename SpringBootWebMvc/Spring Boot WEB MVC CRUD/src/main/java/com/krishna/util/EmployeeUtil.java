package com.krishna.util;

import org.springframework.stereotype.Component;

import com.krishna.entity.Employee;

@Component
public class EmployeeUtil {

	public void calculateHraAndTa(Employee employee) {
		double salary = employee.getSalary();
		employee.setHra(salary * 12 / 100.0);
		employee.setTa(salary * 3 / 100.0);
	}

}

package com.krishna.service;

import java.util.List;

import com.krishna.entity.Employee;

public interface EmployeeService {

	Integer saveEmployee(Employee employee);

	List<Employee> getAllEmployees();

	Employee getEmployeeById(Integer id);

	void deleteEmployee(Integer id);

	void updateEmployee(Employee employee);

	boolean isEmployeeEmailExists(String email);

}

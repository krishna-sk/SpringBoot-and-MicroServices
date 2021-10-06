package com.krishna.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.krishna.entity.Employee;

public interface EmployeeService {

	Integer saveEmployee(Employee employee);

	List<Employee> getAllEmployees();

	Page<Employee> getAllEmployees(Pageable pageable);

	Employee getEmployeeById(Integer id);

	void deleteEmployee(Integer id);

	void updateEmployee(Employee employee);

	boolean isEmployeeEmailExists(String email);

}

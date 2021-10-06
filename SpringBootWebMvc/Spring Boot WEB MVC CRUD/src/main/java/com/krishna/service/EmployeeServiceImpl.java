package com.krishna.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.krishna.entity.Employee;
import com.krishna.repository.EmployeeRepository;
import com.krishna.util.EmployeeUtil;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	EmployeeUtil employeeUtil;

	@Override
	public Integer saveEmployee(Employee employee) {

		employeeUtil.calculateHraAndTa(employee);
		return employeeRepository.save(employee).getId();
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public Page<Employee> getAllEmployees(Pageable pageable) {
		return employeeRepository.findAll(pageable);
	}

	@Override
	public Employee getEmployeeById(Integer id) {
		Optional<Employee> employee = employeeRepository.findById(id);
		if (employee.isPresent())
			return employee.get();
		return null;
	}

	@Override
	public void deleteEmployee(Integer id) {
		employeeRepository.deleteById(id);
	}

	@Override
	public void updateEmployee(Employee employee) {
		employeeUtil.calculateHraAndTa(employee);
		employeeRepository.save(employee);
	}

	@Override
	public boolean isEmployeeEmailExists(String email) {
		return employeeRepository.getEmployeeEmailCount(email) > 0;
	}

}

package com.krishna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.krishna.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	@Query("SELECT count(e.email) FROM Employee e WHERE e.email=:email")
	public Integer getEmployeeEmailCount(String email);
	
}

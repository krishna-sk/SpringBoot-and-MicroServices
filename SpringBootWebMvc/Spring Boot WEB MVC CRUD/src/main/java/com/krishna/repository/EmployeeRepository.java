package com.krishna.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.krishna.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

}

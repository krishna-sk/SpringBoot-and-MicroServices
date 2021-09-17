package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	@Query("SELECT E.empName, P.projName  FROM Employee E INNER JOIN E.pob as P ")
	public List<Object[]> getDataA();
	
	@Query("SELECT E.empName, P.projName  FROM Employee E LEFT OUTER  JOIN E.pob as P ")
	public List<Object[]> getDataB();
	
	@Query("SELECT E.empName, P.projName  FROM Employee E RIGHT JOIN E.pob as P ")
	public List<Object[]> getDataC();
	
	@Query("SELECT E.empName, P.projName  FROM Employee E FULL JOIN E.pob as P ")
	public List<Object[]> getDataD();
}
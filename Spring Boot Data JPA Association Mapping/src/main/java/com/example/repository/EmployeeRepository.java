package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;

import com.example.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	// Queries for joins

	@Query("SELECT E.empName, P.projName  FROM Employee E INNER JOIN E.pob as P ")
	public List<Object[]> getDataA();

	@Query("SELECT E.empName, P.projName  FROM Employee E LEFT OUTER  JOIN E.pob as P ")
	public List<Object[]> getDataB();

	@Query("SELECT E.empName, P.projName  FROM Employee E RIGHT JOIN E.pob as P ")
	public List<Object[]> getDataC();

	@Query("SELECT E.empName, P.projName  FROM Employee E FULL JOIN E.pob as P ")
	public List<Object[]> getDataD();

	// @Procedure for Stored Procedures
	// there is bug in the latest update so it takes time to fix this.
	// @Procedure is only working on single output parameter that to not generic
	// we can use @Query to call stored procedures

	@Procedure("GET_EMPS_COUNT_BY_DEPT")
	Integer getEmployeesCountByDesg(String dept);

	@Query(value ="{call GET_ALL_EMPS ()}",nativeQuery = true)
	List<Employee> getAllEmployees();
	
	@Query(value ="{call GET_ALL_EMP_BY_DEPT (?)}",nativeQuery = true)
	List<Employee> getAllEmployeesByDept(String dept);
	
}
package com.example.runner;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.entity.Employee;

@Component
public class ProcedureCallOrmRunner implements CommandLineRunner {

	@Autowired
	private EntityManager entityManager;

	@Override
	public void run(String... args) throws Exception {

		System.out.println("\n\n + ProcedureCallOrmRunner + \n\n");

		// Case 1
		// 1. Create Stored Procedure Query
		StoredProcedureQuery query1 = entityManager.createStoredProcedureQuery("GET_ALL_EMPS", Employee.class);

		// 2. Register input and output params if exists

		// 3. Provide data for input params

		// 4. Execute and print result
		List<Employee> resultList1 = query1.getResultList();
		resultList1.forEach(System.out::println);

		// Case 2
		// 1. Create Stored Procedure Query
		StoredProcedureQuery query2 = entityManager.createStoredProcedureQuery("GET_ALL_EMP_BY_DEPT", Employee.class);

		// 2. Register input and output params if exists
		query2.registerStoredProcedureParameter("EM_DEPT", String.class, ParameterMode.IN);

		// 3. Provide data for input params
		query2.setParameter("EM_DEPT", "DEV");

		// 4. Execute and print result
		List<Employee> resultList2 = query2.getResultList();
		System.out.println("\n\n\n");
		resultList2.forEach(System.out::println);

		// Case 3
		// 1. Create Stored Procedure Query
		StoredProcedureQuery query3 = entityManager.createStoredProcedureQuery("GET_EMPS_COUNT_BY_DEPT",
				Employee.class);

		// 2. Register input and output params if exists
		query3.registerStoredProcedureParameter("EM_DEPT", String.class, ParameterMode.IN);
		query3.registerStoredProcedureParameter("dept_count", Integer.class, ParameterMode.OUT);

		// 3. Provide data for input params
		query3.setParameter("EM_DEPT", "DEV");

		// 4. Execute and print result
		query3.execute();
		Integer count = (Integer) query3.getOutputParameterValue("dept_count");
		System.out.println("\n\n\n");
		System.out.println(count);

	}

}

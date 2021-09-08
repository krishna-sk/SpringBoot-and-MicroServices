package com.example.runner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.entity.Employee;
import com.example.repository.EmployeeRepository;

@Component
public class EmployeeJPARunner implements CommandLineRunner {

	@Autowired
	private EmployeeRepository employeeRepo;
	
	public void run(String... args) throws Exception {
		System.out.println("\n\n + EmployeeJPARunner +\n\n");
		
		Employee emp = new Employee();
		emp.setEmpId(10);
		emp.setEmpName("AA");
		emp.setEmpSal(20.0);
		
		Set<String> empProjs = new HashSet<>();
		empProjs.add("P1");
		empProjs.add("P2");
		empProjs.add("P3");
		
		emp.setEmpProjs(empProjs);
		
		List<String> empModules = new ArrayList<>();
		empModules.add("MA");
		empModules.add("MB");
		empModules.add("MC");
		
		emp.setEmpModules(empModules);
		
		
		Map<String,String> empClients = new HashMap<>();
		empClients.put("C1", "NIT");
		empClients.put("C2", "XYZ");
		empClients.put("C3", "MNO");
		
		emp.setEmpClients(empClients);
		
		employeeRepo.save(emp);
//		employeeRepo.findAll().forEach(System.out::println);
		
	}

}

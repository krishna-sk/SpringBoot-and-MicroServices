package com.example.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.entity.Employee;
import com.example.entity.Project;
import com.example.repository.EmployeeRepository;
import com.example.repository.ProjectRepository;

@Component
public class ManyToOneRunner implements CommandLineRunner {
	
	@Autowired
	private ProjectRepository projectRepo;
	
	@Autowired
	private EmployeeRepository employeeRepo;

	public void run(String... args) throws Exception {

		// many rows in Employee table are connected with 1 row in project table
				
		System.out.println("\n\n + ManyToOneRunner + \n\n");
		Project p1 = new Project(110, "P-HTC", "NMO", 3.2);
		Project p2 = new Project(112, "P-ORC", "NIT", 1.0);
		
		projectRepo.save(p1);
		projectRepo.save(p2);
		
		Employee e1 = new Employee(50, "AA", "DEV", p1);
		Employee e2 = new Employee(51, "BA", "DEV", p1);
		Employee e3 = new Employee(52, "CA", "QA", p1);
		
		Employee e4 = new Employee(53, "NN", "DEV", p2);
		Employee e5 = new Employee(54, "RR", "QA", p2);
		
		employeeRepo.save(e1);
		employeeRepo.save(e2); 
		employeeRepo.save(e3);
		employeeRepo.save(e4);
		employeeRepo.save(e5);
		
	}

}
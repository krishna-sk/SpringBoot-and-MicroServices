package com.example.runner;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.entity.Employee;
import com.example.entity.Project;
import com.example.repository.EmployeeRepository;
import com.example.repository.ProjectRepository;

@Component
public class JoinsRunner implements CommandLineRunner {
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;

	public void run(String... args) throws Exception {
		
		System.out.println("\n\n + JoinsRunner + \n\n");

		// many rows in Employee table are connected with 1 row in project table
				
		Project p1 = new Project(110, "P-HTC", "NMO", 3.2);
		Project p2 = new Project(112, "P-ORC", "NIT", 1.0);
		Project p3 = new Project(113, "P-TEST", "XYZ", 1.0);
		Project p4 = new Project(114, "P-SAMP", "NIT", 2.0);
		
		projectRepository.save(p1);
		projectRepository.save(p2);
		projectRepository.save(p3);
		projectRepository.save(p4);
		
		Employee e1 = new Employee(50, "AA", "DEV", p1);
		Employee e2 = new Employee(51, "BA", "DEV", p3);
		Employee e3 = new Employee(52, "CA", "QA", p1);
		
		Employee e4 = new Employee(53, "NN", "DEV", null);
		Employee e5 = new Employee(54, "RR", "QA", p2);
		
		employeeRepository.save(e1);
		employeeRepository.save(e2);
		employeeRepository.save(e3);
		employeeRepository.save(e4);
		employeeRepository.save(e5);
		
		//repo.getDataA()
		//repo.getDataB()
		//repo.getDataC()
//		repo.getDataD()
//		.stream()
//		.map(ob->ob[0]+"-"+ob[1]) //Object[]->String
//		.forEach(System.out::println); //print
		
		//System.out.println(repo.getDataA());
		List<Object[]> list = employeeRepository.getDataA();
		for(Object[] ob:list) {
			String s = ob[0]+"-"+ob[1];
			System.out.println(s);
		}
		
	}

}
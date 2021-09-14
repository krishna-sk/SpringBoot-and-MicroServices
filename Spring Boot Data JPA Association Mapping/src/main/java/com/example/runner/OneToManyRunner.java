package com.example.runner;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.entity.Address;
import com.example.entity.Student;
import com.example.repository.AddressRepository;
import com.example.repository.StudentRepository;

@Component
public class OneToManyRunner implements CommandLineRunner {

//	@Autowired
//	private AddressRepository addressRepo;
	
	@Autowired
	private StudentRepository studentRepo;
	
	@Override
	public void run(String... args) throws Exception {
		
		System.out.println("\n\n + OneToManyRunner + \n\n");

		Address a1 = new Address(10, "4-65/B", "AMEERPET", 500013L);
		Address a2 = new Address(11, "32/F-102", "SR NAGAR", 500014L);
		
		Student s1 = new Student(501, "SAM", "Core Java", Arrays.asList(a1,a2));
		
		// we have used Cascading in the entity class. So, no need to save address explicitly.
		
		/*addressRepo.save(a1);
		addressRepo.save(a2);*/
		
		studentRepo.save(s1);
		
		Address a3 = new Address(12, "9-05/T", "KYTP", 500063L);
		Address a4 = new Address(13, "14-15/8A", "RNJG", 500044L);
		
		Student s2 = new Student(502, "SYED", "Adv Java", Arrays.asList(a3,a4));
		
		/*addressRepo.save(a3);
		addressRepo.save(a4);*/
		
		studentRepo.save(s2);
		
	}


}
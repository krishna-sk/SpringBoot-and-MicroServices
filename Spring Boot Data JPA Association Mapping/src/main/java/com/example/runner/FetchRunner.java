package com.example.runner;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.entity.Address;
import com.example.entity.Student;
import com.example.repository.StudentRepository;

@Component
public class FetchRunner implements CommandLineRunner {

	@Autowired
	private StudentRepository studentRepository;

	public void run(String... args) throws Exception {
		
		Address a1 = new Address(100, "4-65/B", "AMEERPET", 500013L);
		Address a2 = new Address(110, "32/F-102", "SR NAGAR", 500014L);
		
		Student s1 = new Student(5010, "SAM", "Core Java", Arrays.asList(a1,a2));
		
		// we have used Cascading in the entity class. So, no need to save address explicitly.
		
		/* we have used FetchType as eager so thats why we can print the student object. If we didnt specify
		* the FetchT.ype by default it will be lazy and we will get lazy intialization exception since we are
		* trying to print the address object also in the toString method of Student class
		*/
		
		
		studentRepository.save(s1);
		
		Address a3 = new Address(120, "9-05/T", "KYTP", 500063L);
		Address a4 = new Address(130, "14-15/8A", "RNJG", 500044L);
		
		Student s2 = new Student(5020, "SYED", "Adv Java", Arrays.asList(a3,a4));
		
		studentRepository.save(s2);

		System.out.println("\n\n + FetchRunner + \n\n");
		studentRepository.findAll().forEach(System.out::println);

	}

}
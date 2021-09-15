package com.example.runner;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.entity.Course;
import com.example.entity.User;
import com.example.repository.CourseRepository;
import com.example.repository.UserRepository;

@Component
public class ManyToMayRunner implements CommandLineRunner {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private CourseRepository courseRepo;
	
	@Override
	public void run(String... args) throws Exception {
		
		System.out.println("\n\n + ManyToMayRunner + \n\n");
		
		Course c1 = new Course(100, "Core Java", 1000.0);
		Course c2 = new Course(101, "Adv Java", 1500.0);
		Course c3 = new Course(102, "Hibernate", 2500.0);
		Course c4 = new Course(103, "Boot and MS", 3500.0);
		
		courseRepo.save(c1);
		courseRepo.save(c2);
		courseRepo.save(c3);
		courseRepo.save(c4);
		
		User s1 = new User(51, "SAM", "HYD", Arrays.asList(c2,c4));
		User s2 = new User(52, "SYED", "BAN", Arrays.asList(c1,c3));
		User s3 = new User(53, "RAM", "DHL", Arrays.asList(c1,c4));
		User s4 = new User(54, "AJAY", "MUM", Arrays.asList(c2,c3));
		
		userRepo.save(s1);
		userRepo.save(s2);
		userRepo.save(s3);
		userRepo.save(s4);
	}

}
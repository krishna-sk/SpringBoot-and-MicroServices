package com.example.runner;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.example.entity.User;
import com.example.repository.UserRepository;

@Component
public class UserPaginationRunner implements CommandLineRunner {

	@Autowired
	private UserRepository userRepo;
	
	public void run(String... args) throws Exception {
		
		System.out.println("\n\n + UserPaginationRunner +\n\n");
		
		userRepo.saveAll(Arrays.asList(new User(10, "Employee 1", 200.0), new User(11, "Employee 2", 500.0),
				new User(12, "Employee 3", 500.0), new User(13, "Employee 4", 500.0), new User(14, "Employee 5", 500.0),
				new User(15, "Employee 6", 150.0), new User(16, "Employee 7", 50.0)));
		
		
		
		//Pagination input (page number , page size)
		Pageable pageable = PageRequest.of(0, 3);
		
		//execute
		Page<User> page = userRepo.findAll(pageable);
		
		//print data
		if(page.hasContent()) {
			List<User> list = page.getContent();
			list.forEach(System.out::println);
		} else {
			System.out.println("Given page not exist");
		}
		
		//---------------meta data-------------
		System.out.println("HAVIG DATA? " + page.hasContent());
		System.out.println("IS EMPTY? " + page.isEmpty());
		System.out.println("IS FIRST? " + page.isFirst());
		System.out.println("IS LAST? " + page.isLast());
		System.out.println("HAS NEXT? " + page.hasNext());
		System.out.println("HAS PREVIOUS? " + page.hasPrevious());
		System.out.println("TOTAL PAGES? " + page.getTotalPages());
		System.out.println("TOTAL ROWS? " + page.getTotalElements());
		System.out.println("CURRENT PAGE NUMBER? " + page.getNumber()); 
	}

}
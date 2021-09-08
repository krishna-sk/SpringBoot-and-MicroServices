package com.example.runner;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Component;

import com.example.entity.Book;
import com.example.repository.BookRepository;

@Component
public class BookSortRunner implements CommandLineRunner {

	@Autowired
	BookRepository bookRepo;

	@Override
	public void run(String... args) throws Exception {
		
		System.out.println("\n\n + BookSortRunner +\n\n");

		bookRepo.saveAll(Arrays.asList(new Book(10, "Core Java", 200.0), new Book(11, "Adv Java", 500.0),
				new Book(12, "Boot", 500.0), new Book(13, "Microservices", 500.0), new Book(14, "Angular", 500.0),
				new Book(15, "ReactJS", 150.0), new Book(16, "HTML/CSS", 50.0)));

		// case#1 sort using single field
		// SQL: select * from booktab order by bcost asc;

		Sort s1 = Sort.by("bookCost");
		// Sort s1 = Sort.by(Direction.ASC,"bookCost");
		bookRepo.findAll(s1).forEach(System.out::println);

		// SQL: select * from booktab order by bcost desc;

		Sort s2 = Sort.by(Direction.DESC, "bookCost");
		bookRepo.findAll(s2).forEach(System.out::println);

		// case#2 sort using multiple field
		// SQL: select * from booktab order by bcost asc,bname asc;

		Sort s3 = Sort.by("bookCost", "bookName"); // ASC
		// Sort s3 = Sort.by(Direction.ASC,"bookCost","bookName"); //ASC
		bookRepo.findAll(s3).forEach(System.out::println);

		// SQL: select * from booktab order by bcost desc,bname desc;

		Sort s4 = Sort.by(Direction.DESC, "bookCost", "bookName"); // ASC
		bookRepo.findAll(s4).forEach(System.out::println);

		// case#3 sort using multiple field with mixing types
		// SQL: select * from booktab order by bcost asc, bname desc;

		Sort s5 = Sort.by(Order.asc("bookCost"), Order.desc("bookName"));
		bookRepo.findAll(s5).forEach(System.out::println);

		// SQL: select * from booktab order by bcost desc, bname asc;

		Sort s6 = Sort.by(Order.desc("bookCost"), Order.asc("bookName"));
		bookRepo.findAll(s6).forEach(System.out::println);

	}

}

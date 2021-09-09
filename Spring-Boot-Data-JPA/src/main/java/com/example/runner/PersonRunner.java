package com.example.runner;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.entity.Person;
import com.example.repository.PersonRepository;

@Component
public class PersonRunner implements CommandLineRunner{
	
	@Autowired
	PersonRepository personRepo;
	
	@Override
	public void run(String... args) throws Exception {
		
		System.out.println("\n\n + PersonRunner +\n\n");
		
		personRepo.saveAll(Arrays.asList(new Person(100,"ABC",3000.00),new Person(101,"DEF",4000.0),
				new Person(102,"GHI",5000.0),new Person(103,"JKL",6000.0),new Person(104,"MNO",7000.0)));
		
		personRepo.getAllPersons().forEach(System.out::println);
		personRepo.getAllPersonsIds().forEach(System.out::println);
//		personRepo.getAllPersonIdsAndBankBalance().stream().map(object->object[0]+"-"+object[1])
//		.forEach(System.out::println);
		personRepo.getAllPersonIdsAndBankBalance()
		.forEach((object)->System.out.println(object[0]+"---"+object[1]));
	}

}

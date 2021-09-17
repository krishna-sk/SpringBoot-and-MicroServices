package com.example.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.entity.Professor;
import com.example.entity.Profile;
import com.example.repository.ProfessorRepository;

@Component
public class OneToOne implements CommandLineRunner {

	@Autowired
	private ProfessorRepository professorRepo;
	
//	@Autowired
//	private ProfileRepository profileRepo;
//  since we are using cascading no need to save the profile explicitly

	public void run(String... args) throws Exception {
		
		System.out.println("\n\n + OneToOneRunner + \n\n");

		Profile p1 = new Profile(660, "DEV", 5.1, "SSE");
		Profile p2 = new Profile(661, "QA", 7.1, "SQE");

		professorRepo.save(new Professor(101, "AA", 3.2, p1));
		professorRepo.save(new Professor(102, "BB", 8.2, p2));

//		profileRepo.save(p1);
//		profileRepo.save(p2);
	}

}
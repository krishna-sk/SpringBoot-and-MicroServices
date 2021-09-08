package com.example.runner;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.entity.Course;
import com.example.repository.CourseRepository;

@Component
public class CourseRunnerFindBy implements CommandLineRunner {

	@Autowired
	CourseRepository courseRepo;

	@Override
	public void run(String... args) throws Exception {
		
		System.out.println("\n\n + CourseRunnerFindBy +\n\n");
		
		courseRepo.saveAll(Arrays.asList(new Course(101, "PEN", 200.0, "MNO"), new Course(102, "BOOK", 600.0, "XYZ"),
				new Course(103, "TAB", 1200.0, "MNO"), new Course(104, "MOUSE", 500.0, "XYZ"),
				new Course(105, "BTL", 300.0, "MNO"), new Course(106, "CAP", 100.0, null)));
		
//				courseRepo.findByCourseVen("MNO").forEach(System.out::println);
//				courseRepo.findByCourseVenIs("MNO").forEach(System.out::println);
//				courseRepo.findByCourseVenEquals("MNO").forEach(System.out::println);
		
//				courseRepo.findByCourseFeeLessThan(800.0).forEach(System.out::println);
//				courseRepo.findByCourseIdGreaterThanEqual(103).forEach(System.out::println);
//				courseRepo.findByCourseIdNot(103).forEach(System.out::println);
//				courseRepo.findByCourseIdIn(Arrays.asList(101,115,103,106)).forEach(System.out::println);
//				courseRepo.findByCourseIdNotIn(Arrays.asList(101,115,103,106)).forEach(System.out::println);
//				courseRepo.findByCourseVenIsNull().forEach(System.out::println);
//				courseRepo.findByCourseVenIsNotNull().forEach(System.out::println);

//				courseRepo.findByCourseCodeLike("___").forEach(System.out::println);
//				courseRepo.findByCourseCodeLike("B%").forEach(System.out::println);
//				courseRepo.findByCourseCodeStartingWith("B").forEach(System.out::println);

//				courseRepo.findByCourseCodeLike("%E").forEach(System.out::println);
//				courseRepo.findByCourseCodeEndingWith("E").forEach(System.out::println);


//				courseRepo.findByCourseCodeLike("%A%").forEach(System.out::println);
//				courseRepo.findByCourseCodeContaining("A").forEach(System.out::println);

				Optional<Course> opt = courseRepo.findByCourseIdAndCourseCode(101,"PEN");
				if(opt.isPresent()) 
					System.out.println(opt.get());
				else
					System.out.println("no Data");
			
				
//				courseRepo.findByCourseIdOrCourseCode(101,"BOOK").forEach(System.out::println);
//				courseRepo.findByCourseIdBetween(101,104).forEach(System.out::println);
//				courseRepo.findByCourseVenOrderByCourseCode("MNO").forEach(System.out::println);
//				courseRepo.findByCourseVenOrderByCourseCodeDesc("MNO").forEach(System.out::println);
	}

}

package com.example.runner;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.entity.Student;
import com.example.repository.StudentRepository;

@Component
public class StudentCrudRunner implements CommandLineRunner {

	@Autowired
	StudentRepository studentRepo;

	@Override
	public void run(String... args) throws Exception {
		
		System.out.println("\n\n + StudentCrudRunner +\n\n");

		Student student = new Student(100, "ABC", 100.0);
		studentRepo.save(student);

		studentRepo.saveAll(Arrays.asList(new Student(101, "A", 200.0), new Student(102, "B", 300.0),
				new Student(103, "C", 400.0), new Student(103, "E", 800.0), new Student(104, "D", 500.0)));

		Iterable<Student> students = studentRepo.findAll();
		students.forEach((s) -> System.out.println("student :: " + s));
		System.out.println(studentRepo.existsById(101));
		
		studentRepo.findAllById(Arrays.asList(101, 103, 105, 119, 125)).forEach(System.out::println);

		Optional<Student> opt = studentRepo.findById(103);
		if (opt.isPresent()) {
			Student e = opt.get();
			System.out.println("Data is => " + e);
		} else {
			System.out.println("No Data Found");
		}
		
		System.out.println("COUNT =>" + studentRepo.count());
		
		studentRepo.deleteById(103);
		studentRepo.deleteAllById(Arrays.asList(101, 104));
		studentRepo.deleteAll();

	}

}

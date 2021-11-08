package com.resttemplate.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.resttemplate.enitity.Employee;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@GetMapping("/msg")
	public ResponseEntity<String> showMsg() {
		// return new ResponseEntity<String>("HELLO",HttpStatus.OK);
		return ResponseEntity.ok("HELLO FROM GET");
	}

	@GetMapping("/msg/{id}")
	public ResponseEntity<String> showMsgWithPathVariable(@PathVariable Integer id) {
		// return new ResponseEntity<String>("HELLO",HttpStatus.OK);
		return ResponseEntity.ok("HELLO FROM GET => " + id);
	}

	@GetMapping("/findOne")
	public ResponseEntity<Employee> showEmployee() {
		return ResponseEntity.ok(new Employee(101, "AA", 200.0));

	}

	@PostMapping("/create")
	public ResponseEntity<String> saveEmployee(@RequestBody Employee employee) {
		return ResponseEntity.ok("DATA AT PRODUCER==>" + employee);

	}

	@DeleteMapping("/remove/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable Integer id) {
		return ResponseEntity.ok("Employee deleted!" + id);

	}

	@PutMapping("/update")
	public ResponseEntity<String> modifyEmployee(@RequestBody Employee employee) {
		return ResponseEntity.ok("PRODUCER UPDATED==>" + employee);

	}

}

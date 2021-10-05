package com.krishna.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.krishna.entity.Employee;
import com.krishna.service.EmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	// 1. show Register page
	@GetMapping("/register")
	public String showReg(Model model) {
		model.addAttribute("emp", new Employee());
		return "EmployeeRegister";
	}

	// 2. save operation
	@PostMapping("/save")
	public String saveData(@ModelAttribute Employee employee, // reading form data
			Model model) {
		Integer id = employeeService.saveEmployee(employee); // store in db
		String message = "Employee '" + id + "' Created!"; // create message
		model.addAttribute("message", message); // send message to ui
		return "EmployeeRegister";
	}

	// 3. display data
	@GetMapping("/all")
	public String getAll(@RequestParam(required = false) String message, Model model) {
		List<Employee> list = employeeService.getAllEmployees();
		model.addAttribute("list", list);
		model.addAttribute("message", message);
		return "EmployeeData";
	}

	// 4. remove operation
	@GetMapping("/remove")
	public String removeData(@RequestParam Integer id, RedirectAttributes attributes) {
		employeeService.deleteEmployee(id);
		attributes.addAttribute("message", "Employee '" + id + "' deleted");
		return "redirect:all";
	}

}

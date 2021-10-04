package com.krishna.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.krishna.model.Employee;

@Controller
public class EmployeeController {

	// 1. Show Register Form
	@GetMapping("/show")
	public String showReg() {
		return "EmployeeRegister";
	}

	// 2. Read Form data
	@PostMapping("/create")
	public String readData(@ModelAttribute Employee employee, Model model) {
		System.out.println(employee);
		model.addAttribute("eob", employee);
		return "EmployeeData";
	}
}
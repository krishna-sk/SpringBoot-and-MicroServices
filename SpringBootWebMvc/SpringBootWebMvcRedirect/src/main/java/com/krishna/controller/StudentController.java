package com.krishna.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class StudentController {

	@GetMapping("/remove")
	public String deleteStudent(RedirectAttributes attributes) {
		System.out.println("DELETED!");
		attributes.addAttribute("message", "AJAY deleted successfull!");
		return "redirect:all";
	}
	// make a Request /all?sname=AJAY

	@GetMapping("/all")
	public String getAllStudent(@RequestParam(required = false) String message, Model model) {
		System.out.println("FETCHING!");
		model.addAttribute("name", message);
		return "StudentData";
	}
}
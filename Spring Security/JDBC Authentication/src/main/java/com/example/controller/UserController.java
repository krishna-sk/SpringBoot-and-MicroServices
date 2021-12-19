package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

	@GetMapping("/welcome")
	public String showWelcome() {
		return "WelcomePage";
	}
	
	@GetMapping("/home")
	public String showHome() {
		return "HomePage";
	}
	
	@GetMapping("/emp")
	public String showEmp() {
		return "EmpPage";
	}
	
	@GetMapping("/admin")
	public String showAdmin() {
		return "AdminPage";
	}
	
	@GetMapping("/denied")
	public String showDenied() {
		return "DeniedPage";
	}
	
	
}
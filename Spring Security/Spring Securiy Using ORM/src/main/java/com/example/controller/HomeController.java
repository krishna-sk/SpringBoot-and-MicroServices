package com.example.controller;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/welcome")
	public String showWelcome() {
		return "WelcomePage";
	}

	@GetMapping("/home")
	public String showHome() {

		// SecurityContext context = SecurityContextHolder.getContext();
		// Authentication auth = context.getAuthentication();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(auth.getName()); // username
		System.out.println(auth.getAuthorities()); // roles
		return "HomePage";
	}

	@GetMapping("/emp")
	public String showEmp(Principal principal) {
		System.out.println(principal.getName()); // only username
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
package com.example.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.entity.User;
import com.example.service.IUserService;

@Controller
public class HomeController {

	@Autowired
	private IUserService userService;

	@GetMapping("/welcome")
	public String showWelcome() {
		return "WelcomePage";
	}

	@GetMapping("/home")
	public String showHome(HttpSession session, Principal principal) {

		// SecurityContext context = SecurityContextHolder.getContext();
		// Authentication auth = context.getAuthentication();
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		System.out.println(auth.getName()); // username
//		System.out.println(auth.getAuthorities()); // roles

		User user = userService.findUserByEmail(principal.getName()).get();
		// KEY(String),VALUE(Object)
		session.setAttribute("username", user.getDisplayName());
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
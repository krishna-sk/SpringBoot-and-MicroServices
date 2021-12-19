package com.example.controller;

import com.example.entity.User;
import com.example.service.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    // 1. show Register page
    @GetMapping("/register")
    public String showUser() {
        return "UserRegister";
    }

    // 2. save user
    @PostMapping("/save")
    public String saveUser(
            @ModelAttribute User user,
            Model model) {
        Integer id = userService.saveUser(user);
        String message = "User '" + id + "' Created!";
        model.addAttribute("message", message);
        return "UserRegister";
    }
}

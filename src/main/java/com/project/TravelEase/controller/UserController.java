package com.project.TravelEase.controller;

import com.project.TravelEase.model.User;
import com.project.TravelEase.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/users")
public class UserController {
 
    @Autowired
    private UserService userService;
    
    @Autowired
    private PasswordEncoder encoder;
 
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }
 
    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {
    	user.setPassword(encoder.encode(user.getPassword()));
        userService.registerUser(user);
        model.addAttribute("message", "Registration Successful! Please login.");
        return "redirect:/users/login";
    }
 
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }
 
    @GetMapping("/home")
    public String home() {
    	return "home";
    }
    
    @GetMapping("/adminDashboard")
    public String adminDashboard() {
    	return "adminDashboard";
    }
}

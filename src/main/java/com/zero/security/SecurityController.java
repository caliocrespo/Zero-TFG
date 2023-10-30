package com.zero.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.zero.auxiliar.UserAux;
import com.zero.service.UserService;

@Controller
public class SecurityController {
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/login")
	public String login() {
		return "/login";
	}
	
	@PostMapping("/login")
	public String loginPost() {
		return "redirect:/index";
	}
	
	

	@GetMapping("/register")
	public String register() {
		return "/register";		
	}
	
	@PostMapping("/logout")
	public String performLogout() {
	    return "redirect:/index";
	}
	
	
	@ModelAttribute("user")
	public UserAux getNewUser() {
		return new UserAux();
	}
	
	@PostMapping("/register")
	public String newUserAux(@ModelAttribute("user") UserAux newUser) {
			userService.save(newUser);
			return "redirect:/login";
	}
	
}

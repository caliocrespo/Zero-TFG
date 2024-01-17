package com.zero.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zero.auxiliar.UserAux;
import com.zero.domain.UserEntity;
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
	public ModelAndView loginPost() {
		ModelAndView mav = new ModelAndView("/index");
		return mav;
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
	public String newUserAux(@ModelAttribute("user") UserAux newUser) throws Exception {
		String endpoint=null;
		if(userService.findByEmail(newUser.getEmail())!=null) {
			endpoint="redirect:/register?error1";			
		}else if(userService.findByUsername(newUser.getUsername())!=null) {
			endpoint="redirect:/register?error2";	
		}else {
			endpoint="redirect:/login?success";
			userService.save(newUser);
			
		}
		return endpoint;
	}
	
	@GetMapping("/edit")
	public ModelAndView edit() {
		ModelAndView mav;
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		UserEntity user = userService.findByUsername(name);
		
		mav = new ModelAndView("/user/edit");
		mav.addObject("name", user.getName());
		mav.addObject("lastName", user.getLastName());
		mav.addObject("country", user.getCountry());
		mav.addObject("email", user.getEmail());
		mav.addObject("username", user.getUsername());
		
		return mav;
	}
	
	@PostMapping("/save")
	public ModelAndView save(String username, String name, String lastName, String country) {
		ModelAndView mav;
		
		mav = new ModelAndView("redirect:/edit?success");
		
		UserEntity user = userService.findByUsername(username);
		user.setCountry(country);
		user.setLastName(lastName);
		user.setName(name);
		
		userService.saveChanges(user,username);
		return mav;
	}
	
}

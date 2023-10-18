package com.zero.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zero.service.DeveloperService;

import jakarta.annotation.PostConstruct;

@Controller
@RequestMapping("/developers")
public class DeveloperController {
	
	
	@Autowired
    private DeveloperService developerService;


    //@PostConstruct
    public void getAllDeveloper() {
        developerService.getAPIDeveloper();
    }
    
    @GetMapping("/list")
  	public String list(Model model) {
  		
  		
  		
  		return "/developers/list";
  	}
}







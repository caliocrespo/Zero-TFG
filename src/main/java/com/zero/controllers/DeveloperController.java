package com.zero.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.zero.service.DeveloperService;

import jakarta.annotation.PostConstruct;

@Controller
public class DeveloperController {
	
	
	@Autowired
    private DeveloperService developerService;


    //@PostConstruct
    public void getAllDeveloper() {
        developerService.getAPIDeveloper();
    }
    
    @GetMapping("developers/list")
  	public String list(Model model) {
  		return "/developers/list";
  	}
    
    @GetMapping("/developer")
    public ModelAndView developer (@RequestParam int id) {
    	ModelAndView mav = new ModelAndView("/index");
    	return mav;
    }
}







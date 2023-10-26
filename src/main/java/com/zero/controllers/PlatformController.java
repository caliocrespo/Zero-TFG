package com.zero.controllers;


import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zero.service.PlatformService;

import jakarta.annotation.PostConstruct;

@Controller
@RequestMapping("/platforms")
public class PlatformController {
	
	@Autowired
	private PlatformService platformService;
	
	@Autowired
	private GameController gameController;


    //@PostConstruct
    public void getAllPlataforms() throws ParseException {
        platformService.getAPIPlatforms();
    }
    
    @GetMapping("/list")
	public String list(Model model) {
		
		
		
		return "/platforms/list";
	}
}







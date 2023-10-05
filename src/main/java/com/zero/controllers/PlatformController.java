package com.zero.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zero.service.PlataformService;

import jakarta.annotation.PostConstruct;

@Controller
@RequestMapping("/plataform")
public class PlatformController {
	
	@Autowired
	private PlataformService plataformService;


    @PostConstruct
    public void getAllPlataforms() {
        plataformService.getAPIPlatforms();
    }
}







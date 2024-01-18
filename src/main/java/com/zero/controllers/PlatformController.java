package com.zero.controllers;


import java.text.ParseException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.zero.domain.Platform;
import com.zero.service.PlatformService;

import jakarta.annotation.PostConstruct;

@Controller
public class PlatformController {
	
	@Autowired
	private PlatformService platformService;
	
	//@PostConstruct
    public void getAllPlataforms() throws ParseException {
        platformService.getAPIPlatforms();
    }
    
    @GetMapping("/platforms/list")
    public ModelAndView list(@RequestParam(defaultValue = "1") int page) {
    	
    	ModelAndView mav = new ModelAndView("platforms/list");
    	
    	Collection<Platform> platforms;
    	
    	Pageable paging= PageRequest.of(page-1, 9);
    	
    	Page<Platform> pPlatform = platformService.findAllPagining(paging);
    	
    	platforms = pPlatform.getContent();
    	
    	mav.addObject("platforms", platforms);
    	mav.addObject("currentPage", pPlatform.getNumber()+1);
    	mav.addObject("totalItems", pPlatform.getTotalElements());
    	mav.addObject("totalPages", pPlatform.getTotalPages());
    	mav.addObject("pageSize", 9);

		return mav;
    }
}







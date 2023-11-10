package com.zero.controllers;


import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.zero.domain.Developer;
import com.zero.domain.Genre;
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
    
    @GetMapping("/developers/list")
    public ModelAndView list(@RequestParam(defaultValue = "1") int page) {
    	
    	ModelAndView mav = new ModelAndView("developers/list");
    	
    	Collection<Developer> developers;
    	
    	Pageable paging= PageRequest.of(page-1, 12);
    	
    	Page<Developer> pDeveloper = developerService.findAllPagining(paging);
    	
    	developers = pDeveloper.getContent();
    	
    	mav.addObject("developers", developers);
    	mav.addObject("currentPage", pDeveloper.getNumber()+1);
    	mav.addObject("totalItems", pDeveloper.getTotalElements());
    	mav.addObject("totalPages", pDeveloper.getTotalPages());
    	mav.addObject("pageSize", 12);

		return mav;
    }
    
    @GetMapping("/developer")
    public ModelAndView developer (@RequestParam int id) {
    	ModelAndView mav = new ModelAndView("/index");
    	return mav;
    }
}







package com.zero.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zero.service.GenreService;

import jakarta.annotation.PostConstruct;

@Controller
@RequestMapping("/genre")
public class GenreController {
	
	@Autowired
    private GenreService genreService;


    @PostConstruct
    public void getAllGenres() {
        genreService.getAPIGenres();
    }
}







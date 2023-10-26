package com.zero.controllers;


import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.zero.domain.Game;
import com.zero.domain.Genre;
import com.zero.service.GenreService;

import jakarta.annotation.PostConstruct;

@Controller
public class GenreController {
	
	@Autowired
    private GenreService genreService;


    //@PostConstruct
    public void getAllGenres() {
        genreService.getAPIGenres();
    }
    
    @GetMapping("/genres/list")
    public ModelAndView list(@RequestParam(defaultValue = "1") int page) {
    	
    	ModelAndView mav = new ModelAndView("genres/list");
    	
    	Collection<Genre> genres;
    	
    	Pageable paging= PageRequest.of(page-1, 12);
    	
    	Page<Genre> pGenre = genreService.findAllPagining(paging);
    	
    	genres = pGenre.getContent();
    	
    	mav.addObject("genres", genres);
    	mav.addObject("currentPage", pGenre.getNumber()+1);
    	mav.addObject("totalItems", pGenre.getTotalElements());
    	mav.addObject("totalPages", pGenre.getTotalPages());
    	mav.addObject("pageSize", 12);

		return mav;
    }
}







package com.zero.controllers.api;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zero.auxiliar.GenreAPI;
import com.zero.service.GenreService;
@RestController
@RequestMapping("/api/genres")
public class GenreAPIController {

	@Autowired
	private GenreService genreService;
	
	
	@GetMapping
	public Collection<GenreAPI> getGames(@RequestParam(required = false) Integer id, @RequestParam (required=false) String name){
		List<GenreAPI> genres= genreService.genresApi();
		
		
		
		genres = genres.stream().filter(genre -> (id == null || genre.getId() == id.intValue()) 
				&& name == null || genre.getSlug().equalsIgnoreCase(name)
				).collect(Collectors.toList());
		
		return  genres;
	}
}

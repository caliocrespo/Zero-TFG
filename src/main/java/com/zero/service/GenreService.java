package com.zero.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.zero.auxiliar.GenreAPI;
import com.zero.auxiliar.GenreList;
import com.zero.repository.GenreRepository;
import com.zero.domain.Genre;

import jakarta.transaction.Transactional;

@Service
//@Transactional
public class GenreService {
	
	private final String RAWG_URL= "https://api.rawg.io/api/genres?key=bc332b7707a14fefbfbdc66dc64bb588";
	
	@Autowired
	private GenreRepository genreRepository;
	
	@Autowired
	private final RestTemplate restTemplate;
	
	//Own Repository
	
	
	
	//Others repositories/services
	
	//Constructor
	public GenreService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	//Create method
	
	//Finds method
	
	//Others method
	public void getAPIGenres() {
	    System.out.println("hola");
	    ResponseEntity<GenreList> responseEntity = restTemplate.exchange(
	        RAWG_URL, HttpMethod.GET, null, GenreList.class);

	    if (responseEntity.getStatusCode().is2xxSuccessful()) {
	        GenreList genreList = responseEntity.getBody();
	        if (genreList != null && genreList.getResults() != null) {
	            List<GenreAPI> genres = genreList.getResults();
	            for (GenreAPI gAPI : genres) {
	            	System.out.println(gAPI.getSlug()+gAPI.getDescription()+gAPI.getImageBackground());
	                Genre addGenre = new Genre();
	                addGenre.setName(gAPI.getName());
	                addGenre.setDescription(gAPI.getDescription());
	                genreRepository.save(addGenre);
	            }
	        }
	    }
	}

}

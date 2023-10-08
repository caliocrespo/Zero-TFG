package com.zero.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.zero.auxiliar.GenreAPI;
import com.zero.auxiliar.GenreList;
import com.zero.repository.GenreRepository;
import com.zero.domain.Genre;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class GenreService {
	
	private final String RAWG_URL= "https://api.rawg.io/api/genres?key=e63df09f5ae744498fb5a5ee6d3ca236";
	
	@Autowired
	private final RestTemplate restTemplate;
	
	//Own Repository
	@Autowired
	private GenreRepository genreRepository;
	
	
	//Others repositories/services
	
	//Constructor
	public GenreService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	//Create method
	
	//Finds method
	
	//Others method
	public void getAPIGenres() {
	    ResponseEntity<GenreList> responseEntity = restTemplate.exchange(RAWG_URL, HttpMethod.GET, null, GenreList.class);
	    if (responseEntity.getStatusCode().is2xxSuccessful()) {
	        GenreList genreList = responseEntity.getBody();
	        if (genreList != null && genreList.getResults() != null) {
	            List<GenreAPI> genres = genreList.getResults();
	            for (GenreAPI gAPI : genres) {
	            	
	            	if(genreRepository.findByGenreSlug(gAPI.getSlug())==null) {
		            	String RAWG_URL2= "https://api.rawg.io/api/genres/"+gAPI.getId()+"?key=e63df09f5ae744498fb5a5ee6d3ca236";
		            	ResponseEntity<GenreAPI> responseEntity2 = restTemplate.exchange(RAWG_URL2,
		            			HttpMethod.GET, null, GenreAPI.class);
		            	GenreAPI aux= responseEntity2.getBody();
		                Genre addGenre = new Genre();
		                addGenre.setName(aux.getName());
		                addGenre.setDescription(aux.getDescription());
		                addGenre.setSlug(aux.getSlug());
		                genreRepository.save(addGenre);
		            }
	            }
	        }
	    }
	}

}

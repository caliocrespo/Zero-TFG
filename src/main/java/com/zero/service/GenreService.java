package com.zero.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zero.repository.GenreRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class GenreService {
	
	//Own Repository
	
	@Autowired
	private GenreRepository genreRepository;
	
	//Others repositories/services
	
	//Constructor
	
	public GenreService() {
		super();
	}
	
	//Create method
	
	//Finds method
	
	//Others method

}

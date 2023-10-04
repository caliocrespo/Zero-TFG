package com.zero.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zero.repository.PlataformRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PlataformService {
	
	//Own Repository
	
	@Autowired
	private PlataformRepository plataformRepository;
	
	//Others repositories/services
	
	//Constructor
	
	public PlataformService() {
		super();
	}
	
	//Create method
	
	//Finds method
	
	//Others method

}

package com.zero.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zero.repository.DeveloperRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DeveloperService {
	
	//Own Repository
	
	@Autowired
	private DeveloperRepository developerRepository;
	
	//Others repositories/services
	
	//Constructor
	
	public DeveloperService() {
		super();
	}
	
	//Create method
	
	//Finds method
	
	//Others method

}

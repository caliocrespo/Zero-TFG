package com.zero.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zero.repository.ListRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ListService {
	
	//Own Repository
	
	@Autowired
	private ListRepository listRepository;
	
	//Others repositories/services
	
	//Constructor
	
	public ListService() {
		super();
	}
	
	//Create method
	
	//Finds method
	
	//Others method

}

package com.zero.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zero.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService {
	
	//Own Repository
	
	@Autowired
	private UserRepository userRepository;
	
	//Others repositories/services
	
	//Constructor
	
	public UserService() {
		super();
	}
	
	//Create method
	
	//Finds method
	
	//Others method

}

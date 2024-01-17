package com.zero.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.zero.auxiliar.UserAux;
import com.zero.domain.Role;
import com.zero.domain.UserEntity;
import com.zero.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService implements UserDetailsService{
	
	//Own Repository
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	//Others repositories/services
	
	//Constructor
	
	public UserService() {
		super();
	}

	
	//Create method
	
	public void save(UserAux newUser) {
		UserEntity user = new UserEntity(newUser.getName(),newUser.getLastName(), newUser.getEmail(), newUser.getUsername()
				, passwordEncoder.encode(newUser.getPassword()),newUser.getCountry(), newUser.getImage(), Arrays.asList(new Role("USER")));
		
		userRepository.save(user);
		
	}
	
	public void saveChanges(UserEntity userChange, String username) {
		UserEntity user = this.findByUsername(username);
		user.setCountry(userChange.getCountry());
		user.setLastName(userChange.getLastName());
		user.setName(userChange.getName());
		userRepository.save(user);
	}
	
	//Finds method
	
	public UserEntity findByEmail(String email) {
		UserEntity user;
		
		user = this.userRepository.findByEmail(email);
		
		return user;
	}
	
	
	public UserEntity findByUsername(String username) {
		UserEntity user;
		
		user = this.userRepository.findByUsername(username);
		
		return user;
	}
	
	
	
	//Others method
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = userRepository.findByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException("Username or password invalid");
		}
		
		
		return new User(user.getUsername(), user.getPassword(),mapRoleAuthorities(user.getRoles())) ;
	}
	
	private Collection<? extends GrantedAuthority> mapRoleAuthorities(Collection<Role> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
	

}

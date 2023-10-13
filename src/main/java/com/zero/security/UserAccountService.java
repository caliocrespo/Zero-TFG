package com.zero.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserAccountService implements UserDetailsService{
	
	@Autowired
	private UserAccountRepository userAccountRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Assert.notNull(username);
		
		UserDetails result;
		
		result = this.userAccountRepository.findByUsername(username);
		Assert.notNull(result);
		
		Assert.notNull(result.getAuthorities());
		
		return result;
	}
	
	
}

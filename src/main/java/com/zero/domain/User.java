package com.zero.domain;

import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.zero.security.UserAccount;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class User extends DomainEntity {
	
	private String name;
	private String lastName;
	private String email;
	private Date birthDate;
	private String country;
	
	private UserAccount userAccount;
	
	//---------Getters-----------
	
	@NotBlank
	public String getName() {
		return name;
	}
	@NotBlank
	public String getLastName() {
		return lastName;
	}
	@NotBlank
	@Email
	public String getEmail() {
		return email;
	}

	public Date getBirthDate() {
		return birthDate;
	}
	public String getCountry() {
		return country;
	}
	
	//------------Setters---------------
	public void setName(String name) {
		this.name = name;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	
	//-------------Relationships----------------
	
	public UserAccount getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}
	
	


}

package com.zero.auxiliar;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserAux {
	@NotBlank
	private String name;
	@NotBlank
	private String lastName;
	@Email
	@NotBlank
	private String email;
	@NotBlank
	private String username;
	@NotBlank
	private String password;
	private String country;
	private String image;
	
	//----------Getters-----------
	
	
	public String getName() {
		return name;
	}
	public String getLastName() {
		return lastName;
	}
	public String getEmail() {
		return email;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getCountry() {
		return country;
	}
	public String getImage() {
		return image;
	}
	
	//----------Setters-----------

	
	public void setName(String name) {
		this.name = name;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	
	public UserAux(@NotBlank String name, @NotBlank String lastName, @Email @NotBlank String email,
			@NotBlank String username, @NotBlank String password, String country, String image) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
		this.password = password;
		this.country = country;
		this.image = image;
	}
	public UserAux() {
		// TODO Auto-generated constructor stub
	}
	
	
}

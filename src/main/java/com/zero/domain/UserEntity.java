package com.zero.domain;


import java.util.Collection;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


@Entity
@Access(AccessType.PROPERTY)
@Table(name= "user")
public class UserEntity extends DomainEntity{
	
	
	
	private String name;
	private String lastName;
	private String email;
	private String username;
	private String password;
	private String country;
	private String image;
	private Collection<Role> roles;
	
	
	private Collection<Progress> progress;
	private Collection<GameList> lists;
	
	//private Set<Role> roles;

	public UserEntity(@NotBlank String name, @NotBlank String lastName, @Email @NotBlank String email,
			@NotBlank String username, @NotBlank String password, String country, String image,
			Collection<Role> roles) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
		this.password = password;
		this.country = country;
		this.image = image;
		this.roles = roles;
	}
	
	public UserEntity() {
		
	}
	
	//---------Getters--------------
	
	
	
	@NotBlank
	public String getName() {
		return name;
	}

	@NotBlank
	public String getLastName() {
		return lastName;
	}
	
	@Column(unique = true)
	@Email
	@NotBlank
	public String getEmail() {
		return email;
	}
	@Column(unique = true)
	@NotBlank
	public String getUsername() {
		return username;
	}
	@NotBlank
	public String getPassword() {
		return password;
	}

	public String getCountry() {
		return country;
	}

	public String getImage() {
		return image;
	}
	
	//---------Setters--------------


	public void setCountry(String country) {
		this.country = country;
	}

	public void setImage(String image) {
		this.image = image;
	}

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
	
	
	
	//-------------Relationships----------------
	
	@OneToMany(mappedBy="user")
	public Collection<Progress> getProgress() {
		return progress;
	}
	@OneToMany(mappedBy="user")
	public Collection<GameList> getLists() {
		return lists;
	}
	@ManyToMany (fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	public Collection<Role> getRoles() {
		return roles;
	}

	public void setProgress(Collection<Progress> progress) {
		this.progress = progress;
	}

	public void setLists(Collection<GameList> lists) {
		this.lists = lists;
	}

	
	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

}

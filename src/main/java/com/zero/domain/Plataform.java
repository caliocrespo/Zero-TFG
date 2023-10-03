package com.zero.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)

public class Plataform extends DomainEntity{

	private String name;
	private Integer release_year;
	
	@NotBlank
	public String getName() {
		return name;
	}
	public Integer getRelease_year() {
		return release_year;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setRelease_year(Integer release_year) {
		this.release_year = release_year;
	}
	
	
}

package com.zero.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)

public class Platform extends DomainEntity{

	private String name;
	private Integer year_start;
	private String description;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@NotBlank
	public String getName() {
		return name;
	}
	public Integer getRelease_year() {
		return year_start;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setRelease_year(Integer release_year) {
		this.year_start = release_year;
	}
	
	
}

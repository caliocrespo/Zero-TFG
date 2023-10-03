package com.zero.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)

public class List extends DomainEntity{
	
	private String title;
	private String description;
	private boolean shared; //true public, false private
	
	public boolean isShared() {
		return shared;
	}
	@NotBlank
	public String getTitle() {
		return title;
	}
	public String getDescription() {
		return description;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setShared(boolean shared) {
		this.shared = shared;
	}
	

}

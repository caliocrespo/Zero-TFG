package com.zero.security;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Embeddable;

@Embeddable
@Access(AccessType.PROPERTY)
public class Authority implements GrantedAuthority{
	
	//ID auto-generado
	private static final long serialVersionUID = 1L;


	//Diferentes roles, planeado para que sea solo usuario de momento
	private static final String USER = "USER";
	
	
	private String Authority;

	@Override
	public String getAuthority() {
		return Authority;
	}


	public void setAuthority(String authority) {
		Authority = authority;
	}


}

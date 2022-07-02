package com.todo.auth;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import lombok.Setter;

public class ExtendedUser extends User {
	
	

	@Getter
	@Setter
	private Integer idEntreprise;
	
	public ExtendedUser(String username, String password,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		// TODO Auto-generated constructor stub
	}
	
	/*public ExtendedUser(String username, String password,Integer idEntreprise,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		this.idEntreprise = idEntreprise;
	}*/
	
	
}

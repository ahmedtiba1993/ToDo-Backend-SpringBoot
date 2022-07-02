package com.todo.auth;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.todo.model.dto.UtilisateurDto;
import com.todo.service.UtilisateurService;

@Service
public class ApplicationUserDetailsService implements UserDetailsService{

	@Autowired
	private UtilisateurService service;
	
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		UtilisateurDto utilisateur = service.findByEmail(email);
		
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		utilisateur.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRoleName())));
		
		return new ExtendedUser(utilisateur.getEmail() , utilisateur.getMdp() ,authorities);
	
	}

	
}

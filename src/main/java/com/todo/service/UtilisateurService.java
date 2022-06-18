package com.todo.service;

import java.util.List;
import java.util.Optional;

import com.todo.model.dto.PasswordResetTokenDto;
import com.todo.model.dto.UtilisateurDto;

public interface UtilisateurService {

	UtilisateurDto save(UtilisateurDto dto);
	
	UtilisateurDto findById(Integer id);
	
	UtilisateurDto findByEmail (String email);
	
	List<UtilisateurDto> findAll();
	
	void delete (Integer id);
	
	void demandeChangerMdp(String email);
	
	PasswordResetTokenDto veriftokenChangerMdp(Integer idUtilisateur , String token);
	
	void changerMdp(Integer idUtilisateur , String token , String mdp , String confiermerMdp);
		
}

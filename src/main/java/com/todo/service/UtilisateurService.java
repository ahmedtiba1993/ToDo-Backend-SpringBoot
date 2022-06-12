package com.todo.service;

import java.util.List;

import com.todo.model.dto.UtilisateurDto;

public interface UtilisateurService {

	UtilisateurDto save(UtilisateurDto dto);
	
	UtilisateurDto findById(Integer id);
	
	UtilisateurDto findByEmail (String email);
	
	List<UtilisateurDto> findAll();
	
	void delete (Integer id);
	
}

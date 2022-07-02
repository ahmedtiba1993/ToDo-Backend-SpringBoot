package com.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.todo.controller.api.UtilisateurApi;
import com.todo.model.dto.PasswordResetTokenDto;
import com.todo.model.dto.UtilisateurDto;
import com.todo.service.UtilisateurService;

@RestController
@CrossOrigin
public class UtilisateurController implements UtilisateurApi{

	private UtilisateurService utilisateurService;
	
	@Autowired
	public UtilisateurController(UtilisateurService utilisateurService) {
		super();
		this.utilisateurService = utilisateurService;
	}

	@Override
	public UtilisateurDto save(UtilisateurDto dto) {
		// TODO Auto-generated method stub
		return utilisateurService.save(dto);
	}

	@Override
	public UtilisateurDto findById(Integer id) {
		// TODO Auto-generated method stub
		return utilisateurService.findById(id);
	}

	@Override
	public List<UtilisateurDto> findAll() {
		// TODO Auto-generated method stub
		return utilisateurService.findAll();
	}

	@Override
	public void delete(Integer id) {
		utilisateurService.delete(id);
		
	}

	@Override
	public UtilisateurDto findByEmail(String email) {
		// TODO Auto-generated method stub
		return utilisateurService.findByEmail(email);
	}

	@Override
	public void demandeChangerMdp(String email) {
		// TODO Auto-generated method stub
		utilisateurService.demandeChangerMdp(email);
	}
	
	@Override
	public PasswordResetTokenDto veriftokenChangerMdp(Integer idutilisateur, String token) {
		// TODO Auto-generated method stub
		return utilisateurService.veriftokenChangerMdp(idutilisateur , token);
	}

	@Override
	public void changerMdp(Integer idutilisateur ,String token , String mdp , String confirmerMdp) {
		// TODO Auto-generated method stub
		utilisateurService.changerMdp(idutilisateur , token , mdp , confirmerMdp);
		
	}

}

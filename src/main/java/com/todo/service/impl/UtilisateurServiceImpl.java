package com.todo.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.todo.exception.EntityNotFoundException;
import com.todo.exception.ErrorCodes;
import com.todo.exception.InvalidEntityException;
import com.todo.model.Utilisateur;
import com.todo.model.dto.UtilisateurDto;
import com.todo.repository.UtilisateurRepository;
import com.todo.service.UtilisateurService;
import com.todo.validator.UtilisateurValidator;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UtilisateurServiceImpl implements UtilisateurService{

	public UtilisateurRepository utilisateurRepository;
	
	@Autowired
	public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository) {
		super();
		this.utilisateurRepository = utilisateurRepository;
	}

	@Override
	public UtilisateurDto save(UtilisateurDto dto) {
		
		List<String> errors = UtilisateurValidator.validate(dto);
		if(dto.getEmail() != null) {
			Optional<Utilisateur> uti = utilisateurRepository.findUtilisateurByEmail(dto.getEmail());
			if (uti.isEmpty()==false) {
				errors.add("Cette adresse e-mail est déjà utilisée");
			}
		}
			
		if(!errors.isEmpty()) {
			throw new InvalidEntityException("utilisateur n est pas valide",ErrorCodes.UTILISATEUR_NOT_VALID,errors);
		}
		
	    BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
		dto.setMdp(bc.encode(dto.getMdp()));
		
		Utilisateur saveUtilisateur = utilisateurRepository.save(UtilisateurDto.toEntity(dto));	    
		return UtilisateurDto.fromEntity(saveUtilisateur);
		
	}

	@Override
	public UtilisateurDto findById(Integer id) {
		if(id == null) {
			return null;
		}
		
		Optional<Utilisateur>  utilisateur=utilisateurRepository.findById(id);
		UtilisateurDto dto = UtilisateurDto.fromEntity(utilisateur.get());

		return Optional.of(dto).orElseThrow(() ->
			new EntityNotFoundException("aucun utilisateur avec id ="+id+" n est pas trouver dans la bd'",
					ErrorCodes.UTILISATEUR_NOT_FOUND));
	}

	@Override
	@Transactional
	public UtilisateurDto findByEmail(String email) {
		return utilisateurRepository.findUtilisateurByEmail(email)
				.map(UtilisateurDto::fromEntity)
				.orElseThrow(()->new EntityNotFoundException("aucun utilisateur avec email "+email+" n'ete trouve dans le BD",ErrorCodes.UTILISATEUR_NOT_FOUND)
						);
	}

	@Override
	public List<UtilisateurDto> findAll() {
		return utilisateurRepository.findAll().stream()
		.map(UtilisateurDto::fromEntity)
		.collect(Collectors.toList());
	}

	@Override
	public void delete(Integer id) {
		
		utilisateurRepository.deleteById(id);
		
	}

}

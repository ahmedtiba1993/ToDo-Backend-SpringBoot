package com.todo.validator;

import java.util.ArrayList;
import java.util.List;
import org.springframework.util.StringUtils;

import com.todo.model.dto.UtilisateurDto;

public class UtilisateurValidator {

public static List<String> validate(UtilisateurDto utilisateurDto){
		
		List<String> errors = new ArrayList<String>();
		
		if(utilisateurDto == null) {
			
			errors.add("Veuillez renseigner le nom d'utilisateur");
			errors.add("Veuillez renseigner le prenom d'utilisateur");
			errors.add("Veuillez renseigner le mot de passe d'utilisateur");
			errors.add("Veuillez renseigner email d'utilisateur");
			errors.add("Veuillez renseigner la date de naissace d'utilisateur");
			return errors;

		}
		if(!StringUtils.hasLength(utilisateurDto.getNom())) {
			errors.add("Veuillez renseigner le nom d'utilisateur");
		}
		
		if(!StringUtils.hasLength(utilisateurDto.getPrenom())) {
			errors.add("Veuillez renseigner le prenom d'utilisateur");
		}
		
		if(!StringUtils.hasLength(utilisateurDto.getEmail())) {
			errors.add("Veuillez renseigner email d'utilisateur");
		}
		
		if(!StringUtils.hasLength(utilisateurDto.getMdp())) {
			errors.add("Veuillez renseigner le mot de passe d'utilisateur");
		}
		
		if(utilisateurDto.getDateDeNaissance() == null) {
			errors.add("Veuillez renseigner la date de naissace d'utilisateur");
		} 
		
		
		return errors;

	}
}

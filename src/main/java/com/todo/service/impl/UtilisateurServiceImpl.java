package com.todo.service.impl;

import static com.todo.utils.Constants.UTILISATEUR_ENDPOINT;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.todo.exception.EntityNotFoundException;
import com.todo.exception.ErrorCodes;
import com.todo.exception.InvalidEntityException;
import com.todo.model.PasswordResetToken;
import com.todo.model.Utilisateur;
import com.todo.model.dto.*;
import com.todo.model.dto.UtilisateurDto;
import com.todo.repository.PasswordResetTokenRepository;
import com.todo.repository.UtilisateurRepository;
import com.todo.service.UtilisateurService;
import com.todo.validator.UtilisateurValidator;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UtilisateurServiceImpl implements UtilisateurService{

	public UtilisateurRepository utilisateurRepository;
	public PasswordResetTokenRepository passwordResetTokenRepository; 
	
	@Autowired
    public JavaMailSender emailSender;
	
	@Autowired
	public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository,PasswordResetTokenRepository passwordResetTokenRepository) {
		super();
		this.utilisateurRepository = utilisateurRepository;
		this.passwordResetTokenRepository=passwordResetTokenRepository;
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

	@Override
	public void demandeChangerMdp(String email) {
		// TODO Auto-generated method stub
		if(email == null) {
			return;
		}
				
		UtilisateurDto uti = findByEmail(email);
		Optional<PasswordResetToken>  existDemande=passwordResetTokenRepository.findByUtilisateurId(uti.getId());
		if(!existDemande.isEmpty()) {
			passwordResetTokenRepository.deleteById(existDemande.get().getId());
		}
		
		PasswordResetToken passwordReset= new PasswordResetToken();
		passwordReset.setUtilisateur(UtilisateurDto.toEntity(uti));
		String token=UUID.randomUUID().toString();
		passwordReset.setToken(token);
		
		/*Date + 24 hour*/
		Calendar currentTimeNow = Calendar.getInstance();
		currentTimeNow.add(Calendar.HOUR,24);
		Date tenMinsFromNow = currentTimeNow.getTime();
		passwordReset.setExpiryDate(tenMinsFromNow);
		
		PasswordResetToken savePasswordResetToken = passwordResetTokenRepository.save(passwordReset);
		
	        // Create a Simple MailMessage.
	        SimpleMailMessage message = new SimpleMailMessage();
	        String sendemail=email;
	        System.out.print(email);
	        message.setTo(sendemail);
	        message.setSubject("Lien de récupération de compte ToDo");
	        message.setText(token);

	        // Send Message!
	        this.emailSender.send(message); 

	}

	@Override
	public void changerMdp(Integer idUtilisateur , String token , String mdp , String confirmerMdp) {
		// TODO Auto-generated method stub
		
		veriftokenChangerMdp(idUtilisateur, token);
		
		Optional<Utilisateur>  utilisateur=utilisateurRepository.findById(idUtilisateur);
		UtilisateurDto dto = UtilisateurDto.fromEntity(utilisateur.get());

		ArrayList<String> errors = new ArrayList();
		if(mdp.equals(confirmerMdp) == false) {
			errors.add("les mots de passe saisis ne sont pas identiques");
		}
		if(!errors.isEmpty()) {
			throw new InvalidEntityException("erreur",errors);
		}
		

		BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
		dto.setMdp(bc.encode(mdp));

		Utilisateur saveUtilisateur = utilisateurRepository.save(UtilisateurDto.toEntity(dto));

	}

	@Override
	public PasswordResetTokenDto veriftokenChangerMdp(Integer idUtilisateur, String token) {
		// TODO Auto-generated method stub
		ArrayList<String> errors = new ArrayList();
		
		if(idUtilisateur == null) {
			return null;
		}
		
		Optional<PasswordResetToken>  verifToekn=passwordResetTokenRepository.findByUtilisateurId(idUtilisateur);

		if(verifToekn == null) {
			errors.add("il n y a pas une demande avec cette token");
		}
		
		PasswordResetTokenDto dto = PasswordResetTokenDto.fromEntity(verifToekn.get());

		if(verifToekn.get().getToken().equals(token) == false) {
			errors.add("cette token est incorrecte ");
		}
		
		Date aujourdhui = new Date();		
		if(aujourdhui.before(verifToekn.get().getExpiryDate())==false){
			errors.add("la date de token est expired ");
		}
		
		if(!errors.isEmpty()) {
			throw new InvalidEntityException("erreur",errors);
		}
		
		return Optional.of(dto).orElseThrow(() ->
		new EntityNotFoundException("aucun demande"));
				
	}
	

		
	

	


}

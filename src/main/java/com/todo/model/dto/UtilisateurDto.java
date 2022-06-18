package com.todo.model.dto;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import com.todo.model.Utilisateur;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UtilisateurDto {

	private Integer id;

	private String nom;
	
	private String prenom;

	private String email;
	
	private Date dateDeNaissance;
	
	private String mdp;
	
	private String confirmerMdp;
	
	private List<RolesDto> roles;

	
	public static UtilisateurDto fromEntity(Utilisateur utilisateur) {
	
		if(utilisateur == null) {
			return null;
		}
		
		return UtilisateurDto.builder()
				.id(utilisateur.getId())
				.nom(utilisateur.getNom())
				.prenom(utilisateur.getPrenom())
				.email(utilisateur.getEmail())
				.mdp(utilisateur.getMdp())
				.dateDeNaissance(utilisateur.getDateDeNaissance())
				.roles(
						utilisateur.getRoles() != null ?
								utilisateur.getRoles().stream()
								.map(RolesDto::fromEntity)
								.collect(Collectors.toList()) : null
						)
				.build();
	}
	
	public static Utilisateur toEntity (UtilisateurDto utilisateurDto) {
		
		if(utilisateurDto == null) {
			return null;
		}
		
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setId(utilisateurDto.getId());
		utilisateur.setNom(utilisateurDto.getNom());
		utilisateur.setPrenom(utilisateurDto.getPrenom());
		utilisateur.setEmail(utilisateurDto.getEmail());
		utilisateur.setMdp(utilisateurDto.getMdp());
		utilisateur.setDateDeNaissance(utilisateurDto.getDateDeNaissance());
		return utilisateur;
	}
}

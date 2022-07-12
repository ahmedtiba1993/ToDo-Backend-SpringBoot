package com.todo.model.dto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import com.todo.model.GroupeTodo;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GroupeTodoDto {

	public Integer id;
	
	public String nom;
	
	public Date creation;
	
	private UtilisateurDto utilisateur;
	
	private List<LigneGroupeTodoDto> ligneGroupeTodo;
	
	public static GroupeTodoDto fromEntity(GroupeTodo groupeTodo) {
		
		if(groupeTodo == null ) {
			return null;
		}
		return GroupeTodoDto.builder()
				.id(groupeTodo.getId())
				.nom(groupeTodo.getNom())
				.creation(groupeTodo.getCreation())
				.utilisateur(UtilisateurDto.fromEntity(groupeTodo.getUtilisateur()))
				.ligneGroupeTodo(
						groupeTodo.getLigneGroupeTodo() !=null ?
								groupeTodo.getLigneGroupeTodo().stream()
								.map(LigneGroupeTodoDto::fromEntity)
								.collect(Collectors.toList()) :null
						)
				.build();
	}
	
	public static GroupeTodo toEntity(GroupeTodoDto dto) {
		if (dto == null) {
			return null;
		}
		
		GroupeTodo gt = new GroupeTodo();
		gt.setId(dto.getId());
		gt.setNom(dto.getNom());
		gt.setCreation(dto.getCreation());
		gt.setUtilisateur(UtilisateurDto.toEntity(dto.getUtilisateur()));
	
		return gt;
	}
}

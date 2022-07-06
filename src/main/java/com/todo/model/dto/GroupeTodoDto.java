package com.todo.model.dto;

import java.util.Date;

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

	
	public static GroupeTodoDto fromEntity(GroupeTodo groupeTodo) {
		
		if(groupeTodo == null ) {
			return null;
		}
		return GroupeTodoDto.builder()
				.id(groupeTodo.getId())
				.nom(groupeTodo.getNom())
				.creation(groupeTodo.getCreation())
				.utilisateur(UtilisateurDto.fromEntity(groupeTodo.getUtilisateur()))
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

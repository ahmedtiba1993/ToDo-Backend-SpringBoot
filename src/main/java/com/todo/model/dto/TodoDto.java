package com.todo.model.dto;

import java.time.Instant;
import java.util.Date;

import javax.persistence.Column;
import com.todo.model.Todo;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TodoDto {

	private Integer id;

	private String libelleTodo;
	
	private String descriptionTodo;
	
	private Date dateTodo;
	
	private Boolean etatTodo;
	
	private Boolean corbeille;
	
	private UtilisateurDto utilisateur;
	
	public static TodoDto fromEntity(Todo todo) {
		
		if(todo == null) {
			return null;
		}
		return TodoDto.builder()
				.id(todo.getId())
				.libelleTodo(todo.getLibelleTodo())
				.descriptionTodo(todo.getDescriptionTodo())
				.dateTodo(todo.getDateTodo())
				.etatTodo(todo.getEtatTodo())
				.corbeille(todo.getCorbeille())
				.utilisateur(UtilisateurDto.fromEntity(todo.getUtilisateur()))
				.build();
	}
	
	public static Todo toEntity(TodoDto dto) {
		
		if(dto == null) {
			return null;
		}
		

		Todo todo = new Todo();
		todo.setId(dto.getId());
		todo.setLibelleTodo(dto.getLibelleTodo());
		todo.setDescriptionTodo(dto.getDescriptionTodo());
		todo.setDateTodo(dto.getDateTodo());
		todo.setEtatTodo(dto.getEtatTodo());
		todo.setCorbeille(dto.getCorbeille());
		todo.setUtilisateur(UtilisateurDto.toEntity(dto.getUtilisateur()));
		
		return todo;
	}
	
}

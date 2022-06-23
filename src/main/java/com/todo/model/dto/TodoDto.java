package com.todo.model.dto;

import java.time.Instant;

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
	
	private Instant dateTodo;
	
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
				.utilisateur(UtilisateurDto.fromEntity(todo.getUtilisateur()))
				.build();
	}
	
	public static Todo toEntity(TodoDto dto) {
		
		if(dto == null) {
			return null;
		}
		

		Todo todo = new Todo();
		todo.setId(20);
		todo.setLibelleTodo(dto.getLibelleTodo());
		todo.setDescriptionTodo(dto.getDescriptionTodo());
		todo.setDateTodo(dto.getDateTodo());
		todo.setUtilisateur(UtilisateurDto.toEntity(dto.getUtilisateur()));
		
		return todo;
	}
	
}

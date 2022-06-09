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
	
	public static TodoDto fromEntity(Todo todo) {
		
		if(todo == null) {
			return null;
		}
		
		return TodoDto.builder()
				.id(todo.getId())
				.libelleTodo(todo.getLibelleTodo())
				.descriptionTodo(todo.getDescriptionTodo())
				.dateTodo(todo.getDateTodo())
				.build();
	}
	
	public static Todo toEntity(TodoDto dto) {
		
		if(dto == null) {
			return null;
		}
		
		Todo todo = new Todo();
		todo.setId(dto.getId());
		todo.setLibelleTodo(todo.getLibelleTodo());
		todo.setDescriptionTodo(todo.getDescriptionTodo());
		todo.setDateTodo(todo.getDateTodo());
		
		return todo;
	}
	
}

package com.todo.model.dto;

import java.math.BigDecimal;
import com.todo.model.GroupeTodo;
import com.todo.model.LigneGroupeTodo;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LigneGroupeTodoDto {

	private Integer id;
	
	private TodoDto todo;
	
	private GroupeTodoDto groupeTodo;
	
	public static LigneGroupeTodoDto fromEntity(LigneGroupeTodo ligneGroupeTodo) {
		
		return LigneGroupeTodoDto.builder()
				.id(ligneGroupeTodo.getId())
				.todo(TodoDto.fromEntity(ligneGroupeTodo.getTodo()))
				.groupeTodo(GroupeTodoDto.fromEntity(ligneGroupeTodo.getGroupetodo()))
				.build();
	}
	
	public static LigneGroupeTodo toEntity(LigneGroupeTodoDto dto) {
		
		LigneGroupeTodo ligneGroupeTodo = new LigneGroupeTodo();
		ligneGroupeTodo.setId(dto.getId());
		ligneGroupeTodo.setTodo(TodoDto.toEntity(dto.getTodo()));
		ligneGroupeTodo.setGroupetodo(GroupeTodoDto.toEntity(dto.getGroupeTodo()));
		return ligneGroupeTodo;
	}
}

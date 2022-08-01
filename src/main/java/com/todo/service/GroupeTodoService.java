package com.todo.service;

import java.util.List;

import com.todo.model.dto.GroupeTodoDto;
import com.todo.model.dto.TodoDto;

public interface GroupeTodoService {

	GroupeTodoDto save(GroupeTodoDto dto);
	
	GroupeTodoDto findById(Integer id);
	
	List<GroupeTodoDto> findAll();
	
	void delete (Integer id);
	
	List<GroupeTodoDto> findAllByUtilisateurId(Integer id);
	
	void ajouterTodo(TodoDto dto , Integer idGrTodo);
		
	
}

package com.todo.service;

import java.util.List;
import com.todo.model.dto.TodoDto;

public interface TodoService {

	TodoDto save(TodoDto dto);
	
	TodoDto findById(Integer id);
	
	List<TodoDto> findAll();
	
	void delete (Integer id);
	
	List<TodoDto> findAllByUtilisateurId(Integer id);
	
	void changerEtat(Integer id);
	
	List<TodoDto> findfAllEnded(Integer id);
	
	List<TodoDto> findfAllNotEnded(Integer id);
	
	void corbeille(Integer id);
	
	List<TodoDto> findAllCorbeille(Integer id);
	
	void important(Integer id);
	
	List<TodoDto> findAllImportant(Integer id);
	
	
	List<TodoDto> findAllToday(Integer id);
	

	
}

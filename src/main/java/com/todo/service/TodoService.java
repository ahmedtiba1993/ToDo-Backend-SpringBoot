package com.todo.service;

import java.util.List;
import com.todo.model.dto.TodoDto;

public interface TodoService {

	TodoDto save(TodoDto dto);
	
	TodoDto findById(Integer id);
	
	List<TodoDto> findAll();
	
	void delete (Integer id);
}
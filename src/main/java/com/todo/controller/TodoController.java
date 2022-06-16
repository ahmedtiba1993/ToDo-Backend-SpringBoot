package com.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.todo.controller.api.TodoApi;
import com.todo.model.dto.TodoDto;
import com.todo.service.TodoService;

@RestController
@CrossOrigin
public class TodoController implements TodoApi{

	private TodoService todoService;
	
	@Autowired	
	public TodoController(TodoService todoService) {
		this.todoService = todoService;
	}

	@Override
	public TodoDto save(TodoDto dto) {
		return todoService.save(dto);
	}

	@Override
	public TodoDto findById(Integer id) {
		return todoService.findById(id);
	}

	@Override
	public List<TodoDto> findAll() {
		return todoService.findAll();
	}

	@Override
	public void delete(Integer id) {
		todoService.delete(id);
	}

	@Override
	public List<TodoDto> findAllByUtilisateurId(Integer id) {
		// TODO Auto-generated method stub
		return todoService.findAllByUtilisateurId(id);
	}

}

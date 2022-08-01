package com.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
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

	@Override
	public void changerEtat(Integer id) {
		// TODO Auto-generated method stub
		todoService.changerEtat(id) ;
	}

	@Override
	public List<TodoDto> findAllEnded(Integer idUtilisateur) {
		// TODO Auto-generated method stub
		return todoService.findfAllEnded(idUtilisateur);
	}

	@Override
	public List<TodoDto> findAllNotEnded(Integer idUtilisateur) {
		// TODO Auto-generated method stub
		return todoService.findfAllNotEnded(idUtilisateur);
	}

	@Override
	public void corbeille(Integer id) {
		// TODO Auto-generated method stub
		todoService.corbeille(id);
	}

	@Override
	public List<TodoDto> findAllCorbeille(Integer id) {
		// TODO Auto-generated method stub
		return todoService.findAllCorbeille(id);
	}

	@Override
	public void important(Integer id) {
		// TODO Auto-generated method stub
		todoService.important(id);
		
	}

	@Override
	public List<TodoDto> findAllImportant(Integer id) {
		// TODO Auto-generated method stub
		return todoService.findAllImportant(id);
	}


	@Override
	public List<TodoDto> findAllToday(Integer idUtilisateur) {
		// TODO Auto-generated method stub
		return todoService.findAllToday(idUtilisateur);
	}	

}

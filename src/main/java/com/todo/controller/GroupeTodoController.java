package com.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.todo.controller.api.GroupeTodoApi;
import com.todo.model.LigneGroupeTodo;
import com.todo.model.dto.GroupeTodoDto;
import com.todo.model.dto.TodoDto;
import com.todo.service.GroupeTodoService;

@RestController
@CrossOrigin
public class GroupeTodoController implements GroupeTodoApi{

	private GroupeTodoService groupeTodoService;
	
	@Autowired
	public GroupeTodoController(GroupeTodoService groupeTodoService) {
		super();
		this.groupeTodoService = groupeTodoService;
	}

	@Override
	public GroupeTodoDto save(GroupeTodoDto dto) {
		// TODO Auto-generated method stub
		return groupeTodoService.save(dto);
	}

	@Override
	public GroupeTodoDto findById(Integer id) {
		// TODO Auto-generated method stub
		return groupeTodoService.findById(id);
	}

	@Override
	public List<GroupeTodoDto> findAll() {
		// TODO Auto-generated method stub
		return groupeTodoService.findAll();
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		groupeTodoService.delete(id);
	}

	@Override
	public List<GroupeTodoDto> findAllByUtilisateurId(Integer id) {
		// TODO Auto-generated method stub
		return groupeTodoService.findAllByUtilisateurId(id);
	}

	@Override
	public void ajouterTodo(TodoDto dto, Integer id) {
		// TODO Auto-generated method stub
		groupeTodoService.ajouterTodo(dto, id);
	}

}

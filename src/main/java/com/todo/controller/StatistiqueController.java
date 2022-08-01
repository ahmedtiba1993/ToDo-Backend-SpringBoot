package com.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.todo.controller.api.StatistiqueApi;
import com.todo.service.StatistiqueService;

@RestController
@CrossOrigin
public class StatistiqueController implements StatistiqueApi{
	
	private StatistiqueService statistiqueService;

	@Autowired
	public StatistiqueController(StatistiqueService statistiqueService) {
		super();
		this.statistiqueService = statistiqueService;
	}

	@Override
	public Integer totalTodo(Integer id) {
		// TODO Auto-generated method stub
		return statistiqueService.totalTodo(id);
	}

	@Override
	public Integer totalTodoEnded(Integer id) {
		// TODO Auto-generated method stub
		return statistiqueService.totalTodoEnded(id);
	}

	@Override
	public Integer totalTodoNotEnded(Integer id) {
		// TODO Auto-generated method stub
		return statistiqueService.totalTodoNotEnded(id);
	}

	@Override
	public Integer totalGroupeTodo(Integer id) {
		// TODO Auto-generated method stub
		return statistiqueService.totalGroupeTodo(id);
	}

}

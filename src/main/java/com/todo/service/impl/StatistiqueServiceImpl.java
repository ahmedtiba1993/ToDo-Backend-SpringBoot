package com.todo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.repository.GroupeTodoRepository;
import com.todo.repository.TodoRepository;
import com.todo.service.StatistiqueService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StatistiqueServiceImpl implements StatistiqueService{

	private TodoRepository todoRepository;
	private GroupeTodoRepository groupeTodoRepository;

	@Autowired
	public StatistiqueServiceImpl(TodoRepository todoRepository, GroupeTodoRepository groupeTodoRepository) {
		super();
		this.todoRepository = todoRepository;
		this.groupeTodoRepository = groupeTodoRepository;
	}

	@Override
	public Integer totalTodo(Integer id) {
		// TODO Auto-generated method stub
		return todoRepository.totalTodo(id);
	}
	
	@Override
	public Integer totalTodoEnded(Integer id) {
		// TODO Auto-generated method stub
		return todoRepository.totalTodoEnded(id);
	}

	@Override
	public Integer totalTodoNotEnded(Integer id) {
		// TODO Auto-generated method stub
		return todoRepository.totalTodoNotEnded(id);
	}

	@Override
	public Integer totalGroupeTodo(Integer id) {
		// TODO Auto-generated method stub
		return groupeTodoRepository.totalGrTodo(id);
	}

}

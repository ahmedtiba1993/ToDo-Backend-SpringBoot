package com.todo.service;

public interface StatistiqueService {

	Integer totalTodo(Integer id);

	Integer totalTodoEnded(Integer id);

	Integer totalTodoNotEnded(Integer id);
	
	Integer totalGroupeTodo(Integer id);
}

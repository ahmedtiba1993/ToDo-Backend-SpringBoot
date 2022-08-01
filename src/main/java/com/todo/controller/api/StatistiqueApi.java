package com.todo.controller.api;

import static com.todo.utils.Constants.APP_ROOT;

import org.springframework.web.bind.annotation.GetMapping;

import io.swagger.annotations.Api;

@Api(APP_ROOT+"/statistique")
public interface StatistiqueApi {

	@GetMapping(value = APP_ROOT + "/statistique/totalTodo/{id}")
	Integer totalTodo(Integer id);
	
	@GetMapping(value = APP_ROOT + "/statistique/totalTodoEnded/{id}")
	Integer totalTodoEnded(Integer id);
	
	@GetMapping(value = APP_ROOT + "/statistique/totalTodoNotEnded/{id}")
	Integer totalTodoNotEnded(Integer id);
	
	@GetMapping(value = APP_ROOT + "/groupetodo/totalGrTodo/{id}")
	Integer totalGroupeTodo(Integer id);
}

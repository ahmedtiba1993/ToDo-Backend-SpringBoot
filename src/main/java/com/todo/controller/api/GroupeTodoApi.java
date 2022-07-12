package com.todo.controller.api;

import static com.todo.utils.Constants.APP_ROOT;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.todo.model.LigneGroupeTodo;
import com.todo.model.dto.GroupeTodoDto;
import com.todo.model.dto.TodoDto;

import io.swagger.annotations.Api;

@Api(APP_ROOT+"/groupetodo")
public interface GroupeTodoApi {

	@PostMapping(value = APP_ROOT + "/groupetodo/create" , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
	GroupeTodoDto save(@RequestBody GroupeTodoDto dto);
	
	@GetMapping(value = APP_ROOT + "/groupetodo/id/{idTodo}")
	GroupeTodoDto findById(@PathVariable("idTodo") Integer id);
	
	@GetMapping(value = APP_ROOT + "/groupetodo/all")
	List<GroupeTodoDto> findAll();
	
	@DeleteMapping(value = APP_ROOT + "/groupetodo/delete/{idTodo}")
	void delete (@PathVariable("idTodo") Integer id);
	
	@GetMapping(value = APP_ROOT + "/groupetodo/utilsateur/{idTodo}")
	List<GroupeTodoDto> findAllByUtilisateurId(@PathVariable("idTodo") Integer id);
	
	@PostMapping(value = APP_ROOT + "/groupetodo/ajoutertodo/{id}")
	void ajouterTodo(@RequestBody TodoDto dto , @PathVariable("id") Integer id);
}

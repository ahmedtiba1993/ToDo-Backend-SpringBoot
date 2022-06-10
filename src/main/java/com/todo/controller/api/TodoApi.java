package com.todo.controller.api;
import static com.todo.utils.Constants.APP_ROOT;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.todo.model.dto.TodoDto;

public interface TodoApi {

	@PostMapping(value = APP_ROOT + "/todo/create" , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
	TodoDto save(@RequestBody TodoDto dto);
	
	@GetMapping(value = APP_ROOT + "/todo/id/{idTodo}")
	TodoDto findById(@PathVariable("idTodo") Integer id);
	
	@GetMapping(value = APP_ROOT + "/todos/all")
	List<TodoDto> findAll();
	
	@DeleteMapping(value = APP_ROOT + "/todo/delete/{idTodo}")
	void delete (@PathVariable("idTodo") Integer id);
}

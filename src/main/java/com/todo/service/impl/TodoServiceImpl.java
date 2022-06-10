package com.todo.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.exception.EntityNotFoundException;
import com.todo.exception.ErrorCodes;
import com.todo.exception.InvalidEntityException;
import com.todo.model.Todo;
import com.todo.model.dto.TodoDto;
import com.todo.repository.TodoRepository;
import com.todo.service.TodoService;
import com.todo.validator.TodoValidator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TodoServiceImpl implements TodoService{

	private TodoRepository todoRepository;
	
	@Autowired
	public TodoServiceImpl(TodoRepository todoRepository) {
		super();
		this.todoRepository = todoRepository;
	}

	@Override
	public TodoDto save(TodoDto dto) {
		
		List<String> errors = TodoValidator.validate(dto);
		if(!errors.isEmpty()) {
			throw new InvalidEntityException("todo n'est pas valide",ErrorCodes.TODO_NOT_VALID,errors);
		}
		
		return TodoDto.fromEntity(todoRepository.save(TodoDto.toEntity(dto)));
	}

	@Override
	public TodoDto findById(Integer id) {
		if(id == null) {
			return null;
		}
		
		Optional<Todo> todo = todoRepository.findById(id);
		TodoDto dto = TodoDto.fromEntity(todo.get());
		
		return Optional.of(dto).orElseThrow(()->
			new EntityNotFoundException("Aucun Todo avec id : "+id+" n'est trouver dans la BD",ErrorCodes.TODO_NOT_FOUND)
				);
	}

	@Override
	public List<TodoDto> findAll() {

		return todoRepository.findAll().stream()
				.map(TodoDto::fromEntity)
				.collect(Collectors.toList());
	}

	@Override
	public void delete(Integer id) {
		if (id == null) {
			return;
		}
		
		todoRepository.deleteById(id);
	}

}
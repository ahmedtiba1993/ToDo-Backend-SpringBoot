package com.todo.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.todo.exception.ErrorCodes;
import com.todo.exception.InvalidEntityException;
import com.todo.exception.NoSuchElementException;
import com.todo.model.Todo;
import com.todo.model.Utilisateur;
import com.todo.model.dto.TodoDto;
import com.todo.model.dto.UtilisateurDto;
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
		dto.setEtatTodo(false);
		dto.setCorbeille(false);
		return TodoDto.fromEntity(todoRepository.save(TodoDto.toEntity(dto)));

	}

	@Override
	public TodoDto findById(Integer id) {
		if(id == null) {
			return null;
		}
		
		Optional<Todo> todo = todoRepository.findById(id);
		TodoDto dto = TodoDto.fromEntity(todo.get());
		
		
		return Optional.of(dto).orElseThrow(() ->
			new NoSuchElementException("SomelogicalDescription")
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

	@Override
	public List<TodoDto> findAllByUtilisateurId(Integer id) {
		if(id == null) {
			return null;
		}
		return todoRepository.findAllByUtilisateurId(id).stream()
				.map(TodoDto::fromEntity)
				.collect(Collectors.toList());
	}

	@Override
	public void changerEtat(Integer id) {
		// TODO Auto-generated method stub
		if (id == null) {
			return;
		}
		
		Optional<Todo> todo = todoRepository.findById(id);
		TodoDto dto = TodoDto.fromEntity(todo.get());
		dto.setId(id);
		if(dto.getEtatTodo() == false)
		{
			dto.setEtatTodo(true);
		}else {
			dto.setEtatTodo(false);
		}
				
		Todo saveTodo = todoRepository.save(TodoDto.toEntity(dto));
	}

	@Override
	public List<TodoDto> findfAllEnded(Integer id) {
		// TODO Auto-generated method stub
		return todoRepository.findAllEnded(id).stream()
				.map(TodoDto::fromEntity)
				.collect(Collectors.toList());
	}

	@Override
	public List<TodoDto> findfAllNotEnded(Integer id) {
		// TODO Auto-generated method stub
		return todoRepository.findAllNotEnded(id).stream()
				.map(TodoDto::fromEntity)
				.collect(Collectors.toList());
	}

	@Override
	public void corbeille(Integer id) {
		// TODO Auto-generated method stub

		if (id == null) {
			return;
		}
		Optional<Todo> todo = todoRepository.findById(id);
		TodoDto dto = TodoDto.fromEntity(todo.get());

		if(dto.getCorbeille() == false)
		{
			dto.setCorbeille(true);
		}else {
			dto.setCorbeille(false);
		}
		Todo saveTodo = todoRepository.save(TodoDto.toEntity(dto));

	}	

}
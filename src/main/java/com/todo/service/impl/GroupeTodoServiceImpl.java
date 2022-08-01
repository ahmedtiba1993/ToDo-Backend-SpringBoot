package com.todo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.todo.exception.ErrorCodes;
import com.todo.exception.InvalidEntityException;
import com.todo.exception.NoSuchElementException;
import com.todo.model.GroupeTodo;
import com.todo.model.LigneGroupeTodo;
import com.todo.model.Todo;
import com.todo.model.dto.GroupeTodoDto;
import com.todo.model.dto.LigneGroupeTodoDto;
import com.todo.model.dto.TodoDto;
import com.todo.repository.GroupeTodoRepository;
import com.todo.repository.LigneGroupeTodoRepository;
import com.todo.repository.TodoRepository;
import com.todo.service.GroupeTodoService;
import com.todo.service.TodoService;
import com.todo.validator.GroupeTodoValidator;
import com.todo.validator.TodoValidator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GroupeTodoServiceImpl implements GroupeTodoService{

	private GroupeTodoRepository groupeTodoRepository;
	private LigneGroupeTodoRepository ligneGroupeTodoRepository;
	private TodoService todoService; 
	
	@Autowired
	public GroupeTodoServiceImpl(GroupeTodoRepository groupeTodoRepository,
			LigneGroupeTodoRepository ligneGroupeTodoRepository , TodoService todoService) {
		super();
		this.groupeTodoRepository = groupeTodoRepository;
		this.ligneGroupeTodoRepository = ligneGroupeTodoRepository;
		this.todoService = todoService;
	}

	@Override
	public GroupeTodoDto save(GroupeTodoDto dto) {
		List<String> errors = GroupeTodoValidator.validate(dto);
		if(!errors.isEmpty()) {
			throw new InvalidEntityException("Groupe todo n'est pas valide",ErrorCodes.GROUPE_TODO_NOT_VALID,errors);
		}
		
		/*List<String> errorstodo = new ArrayList<String>();	
		if(dto.getLigneGroupeTodo() != null) {
			dto.getLigneGroupeTodo().forEach(LigGrTodo->{
				Optional<Todo> todo = todoRepository.findById(LigGrTodo.getTodo().getId());
				if(todo.isEmpty()) {
					errorstodo.add("todo avec id '"+LigGrTodo.getTodo().getId()+"' n'existe pas");
				}
			});
		}*/
		
		GroupeTodo saveGrTodo = groupeTodoRepository.save(GroupeTodoDto.toEntity(dto));
				
		if(dto.getLigneGroupeTodo() != null) {
				dto.getLigneGroupeTodo().forEach(ligGrTodo ->{
					
					LigneGroupeTodo ligneGroupeTodo=LigneGroupeTodoDto.toEntity(ligGrTodo);
					ligneGroupeTodo.setGroupetodo(saveGrTodo);
					ligneGroupeTodoRepository.save(ligneGroupeTodo);
				});				
		}
		return GroupeTodoDto.fromEntity(saveGrTodo);
	}

	@Override
	public GroupeTodoDto findById(Integer id) {
		// TODO Auto-generated method stub
		if(id==null) {
			return null;
		}
		
		Optional<GroupeTodo> groupeTodo = groupeTodoRepository.findById(id);
		GroupeTodoDto dto = GroupeTodoDto.fromEntity(groupeTodo.get());
		
		return Optional.of(dto).orElseThrow(() ->
		new NoSuchElementException("SomelogicalDescription")
	);
	}

	@Override
	public List<GroupeTodoDto> findAll() {
		return groupeTodoRepository.findAll().stream()
				.map(GroupeTodoDto::fromEntity)
				.collect(Collectors.toList());
	}

	@Override
	public void delete(Integer id) {
		if (id == null){
			return;
		}
		
		groupeTodoRepository.deleteById(id);
	}

	@Override
	public List<GroupeTodoDto> findAllByUtilisateurId(Integer id) {
		// TODO Auto-generated method stub
		return groupeTodoRepository.findAllByUtilisateurId(id).stream()
				.map(GroupeTodoDto::fromEntity)
				.collect(Collectors.toList());
		}

	@Override
	public void ajouterTodo(TodoDto dto, Integer idGrTodo) {
		// TODO Auto-generated method stub
		
		TodoDto tododto = todoService.save(dto);

		Optional<GroupeTodo> groupeTodo = groupeTodoRepository.findById(idGrTodo);

		GroupeTodoDto groupeTodoDto = GroupeTodoDto.fromEntity(groupeTodo.get());

		LigneGroupeTodoDto ligneGroupeTodoDto = LigneGroupeTodoDto.builder()
				.todo(tododto)
				.groupeTodo(groupeTodoDto)
				.build();

		ligneGroupeTodoRepository.save(ligneGroupeTodoDto.toEntity(ligneGroupeTodoDto));
		
	}
	
}

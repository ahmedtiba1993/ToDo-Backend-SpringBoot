package com.todo.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.exception.ErrorCodes;
import com.todo.exception.InvalidEntityException;
import com.todo.exception.NoSuchElementException;
import com.todo.model.GroupeTodo;
import com.todo.model.dto.GroupeTodoDto;
import com.todo.repository.GroupeTodoRepository;
import com.todo.repository.TodoRepository;
import com.todo.service.GroupeTodoService;
import com.todo.validator.GroupeTodoValidator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GroupeTodoServiceImpl implements GroupeTodoService{

	private GroupeTodoRepository groupeTodoRepository;
	
	@Autowired
	public GroupeTodoServiceImpl(GroupeTodoRepository groupeTodoRepository) {
		super();
		this.groupeTodoRepository = groupeTodoRepository;
	}

	@Override
	public GroupeTodoDto save(GroupeTodoDto dto) {
		List<String> errors = GroupeTodoValidator.validate(dto);
		if(!errors.isEmpty()) {
			throw new InvalidEntityException("Groupe todo n'est pas valide",ErrorCodes.GROUPE_TODO_NOT_VALID,errors);
		}
				
		return GroupeTodoDto.fromEntity(groupeTodoRepository.save(GroupeTodoDto.toEntity(dto)));
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

}

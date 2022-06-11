package com.todo.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.todo.model.dto.TodoDto;

public class TodoValidator {
	
	public static List<String> validate(TodoDto dto){
		
		List<String> errors = new ArrayList<String>();
		
		if(dto == null){
			
			errors.add("Veillez renseigner le libelle");
			errors.add("Veillez renseigner la description");
			errors.add("la date est obligatoire");

		}
		
		if(!StringUtils.hasLength(dto.getLibelleTodo())) {
			errors.add("Veillez renseigner le libelle");
		}
		
		if(!StringUtils.hasLength(dto.getDescriptionTodo())) {
			errors.add("Veillez renseigner la description");
		}
		
		if(dto.getDateTodo() == null) {
			errors.add("la date est obligatoire");
		}
		
		return errors;
	}

}

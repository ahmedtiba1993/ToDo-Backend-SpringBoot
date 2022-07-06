package com.todo.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.todo.model.dto.GroupeTodoDto;

public class GroupeTodoValidator {

	public static List<String> validate(GroupeTodoDto dto){
		
		List<String> errors = new ArrayList<String>();
		
		if(!StringUtils.hasLength(dto.getNom())) {
			errors.add("Veillez renseigner le nom d'un groupe todo");
		}

		return errors;
	}
}

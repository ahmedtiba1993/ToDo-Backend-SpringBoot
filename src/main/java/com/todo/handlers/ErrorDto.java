package com.todo.handlers;

import java.util.ArrayList;
import java.util.List;
import com.todo.exception.ErrorCodes;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ErrorDto {

	private Integer httpCode;
	
	private ErrorCodes code;
	
	private String message; 
	
	private List<String> errors = new ArrayList<>();
}

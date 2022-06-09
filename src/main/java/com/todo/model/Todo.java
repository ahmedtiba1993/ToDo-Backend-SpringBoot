package com.todo.model;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name="todo")
public class Todo extends AbstractEntity{
	
	@Column(name = "libelleTodo")
	private String libelleTodo;
	
	@Column(name = "desciptionTodo")
	private String descriptionTodo;
	
	@Column(name = "dateTodo")
	private Instant dateTodo;
	
}

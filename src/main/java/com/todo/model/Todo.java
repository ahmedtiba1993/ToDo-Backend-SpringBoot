package com.todo.model;

import java.time.Instant;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="todo")
public class Todo extends AbstractEntity{
	
	private String libelleTodo;
	
	private String descriptionTodo;
	
	private Date dateTodo;
	
	private Boolean etatTodo;
	
	@ManyToOne
	@JoinColumn(name="idutilisateur")
	private Utilisateur utilisateur;
	
}

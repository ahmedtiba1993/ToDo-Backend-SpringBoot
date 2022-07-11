package com.todo.model;

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
@Table(name="lignegroupetodo")
public class LigneGroupeTodo extends AbstractEntity{
	
	@ManyToOne
	@JoinColumn(name="idtodo")
	private Todo todo;
	
	@ManyToOne
	@JoinColumn(name="idgroupetodo")
	private GroupeTodo groupetodo;
	
	
}

package com.todo.model;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
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
@Table(name="utilisateur")
public class Utilisateur extends AbstractEntity{
	
	@Column(name="nom")
	private String nom;
	
	@Column(name="prenom")
	private String prenom;

	@Column(name="email")
	private String email;
	
	@Column(name="dateDeNaissance")
	private Date dateDeNaissance;
	
	@Column(name="mdp")
	private String mdp;
	
	@OneToMany(mappedBy = "utilisateur")
	private List<Roles> roles;
	
	@OneToMany(mappedBy ="utilisateur")
	private List<Todo> todo;
		
}

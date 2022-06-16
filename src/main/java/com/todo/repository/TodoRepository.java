package com.todo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todo.model.Todo;
import com.todo.model.Utilisateur;

public interface TodoRepository extends JpaRepository<Todo, Integer>{
	
	List<Todo> findAllByUtilisateurId(Integer id);

}

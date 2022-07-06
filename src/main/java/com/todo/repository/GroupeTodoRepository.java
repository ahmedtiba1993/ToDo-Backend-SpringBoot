package com.todo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todo.model.GroupeTodo;

public interface GroupeTodoRepository extends JpaRepository<GroupeTodo, Integer>{

	List<GroupeTodo> findAllByUtilisateurId(Integer id);
}

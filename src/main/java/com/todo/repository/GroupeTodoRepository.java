package com.todo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.todo.model.GroupeTodo;

public interface GroupeTodoRepository extends JpaRepository<GroupeTodo, Integer>{

	List<GroupeTodo> findAllByUtilisateurId(Integer id);
	
	@Query(value="SELECT COUNT(*) FROM GroupeTodo t where idutilisateur= :id")
	Integer totalGrTodo(@Param("id") Integer id);
}

package com.todo.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.todo.model.Todo;
import com.todo.model.Utilisateur;

public interface TodoRepository extends JpaRepository<Todo, Integer>{
	
	List<Todo> findAllByUtilisateurId(Integer id);
	
	@Query(value ="select t from Todo t where t.etatTodo = true and idutilisateur= :id and corbeille= false")
	List<Todo> findAllEnded(@Param("id") Integer id);
	
	@Query(value ="select t from Todo t where t.etatTodo = false and idutilisateur= :id and corbeille=false")
	List<Todo> findAllNotEnded(@Param("id") Integer id);
	
	@Query(value ="select t from Todo t where idutilisateur= :id and corbeille=true")
	List<Todo> findAllCorbeille(@Param("id") Integer id);
	
	@Query(value ="select t from Todo t where idutilisateur= :id and important=true")
	List<Todo> findAllImportant(@Param("id") Integer id);
	
	@Query(value="SELECT COUNT(*) FROM Todo t where idutilisateur= :id")
	Integer totalTodo(@Param("id") Integer id);
	
	@Query(value = "SELECT t from Todo t where dateTodo = :today")
	public List<Todo> findAllToday(@Param("today")Date todaysDate);
	
	@Query(value="SELECT COUNT(*) FROM Todo t where idutilisateur= :id and etatTodo = true")
	Integer totalTodoEnded(@Param("id") Integer id);
	
	@Query(value="SELECT COUNT(*) FROM Todo t where idutilisateur= :id and etatTodo = false")
	Integer totalTodoNotEnded(@Param("id") Integer id);
}

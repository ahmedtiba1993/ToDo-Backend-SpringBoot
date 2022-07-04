package com.todo.controller.api;
import static com.todo.utils.Constants.APP_ROOT;
import java.util.List;

import javax.persistence.PostUpdate;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.todo.model.dto.TodoDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(APP_ROOT+"/todo")
public interface TodoApi {

	@PostMapping(value = APP_ROOT + "/todo/create" , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Enregistrer un todo" , notes=" Cette methode permet d'enregistrer ou modificer un todo" , response =TodoDto.class)
	@ApiResponses(value= {
			@ApiResponse(code = 200 , message ="l'objet todo cree / modifie"),
			@ApiResponse(code = 404 , message ="L'objet todo n'est pas valide ")

	})
	TodoDto save(@RequestBody TodoDto dto);
	
	@ApiOperation(value = "Rechercher un todo par Id" , notes=" Cette methode permet de recherhcer un todo par son Id" , response =TodoDto.class)
	@ApiResponses(value= {
			@ApiResponse(code = 200 , message ="todo a ete trouve dans la BDD "),
			@ApiResponse(code = 404 , message ="Aucun todo n'esxiste dans la BDD avec l'id fourni ")

	})
	@GetMapping(value = APP_ROOT + "/todo/id/{idTodo}")
	TodoDto findById(@PathVariable("idTodo") Integer id);
	
	
	@ApiOperation(value = "Renvoi la liste des todo" , notes=" Cette methode permet de charcher et renvoyer la liste des todo qui existant dans la BDD" , responseContainer ="List<TodoDto>")
	@ApiResponses(value= {
			@ApiResponse(code = 200 , message ="La liste des todo / une liste vide "),

	})
	@GetMapping(value = APP_ROOT + "/todos/all")
	List<TodoDto> findAll();
	
	@ApiOperation(value = "Supprimer un todo" , notes=" Cette methode permet de supprimer un todo par ID")
	@ApiResponses(value= {
			@ApiResponse(code = 200 , message ="todo a ete supprimer"),

	})
	@DeleteMapping(value = APP_ROOT + "/todo/delete/{idTodo}")
	void delete (@PathVariable("idTodo") Integer id);
	
	@ApiOperation(value = "Renvoi la liste des todo d'un utilisateur" , notes="Cette methode permet de charcher et renvoyer la liste des todo d'un utilisateur qui existant dans la BDD", responseContainer ="List<TodoDto>")
	@ApiResponses(value= {
			@ApiResponse(code = 200 , message ="todo a ete supprimer"),

	})
	@GetMapping(value = APP_ROOT + "/todos/utilsateur/{idTodo}")
	List<TodoDto> findAllByUtilisateurId(@PathVariable("idTodo") Integer id);
	
	@PostMapping(value = APP_ROOT + "/todo/changeretat/{idTodo}")
	void changerEtat(@PathVariable("idTodo") Integer id);
	
	@GetMapping(value = APP_ROOT + "/todos/allEnded/{idUtilisateur}")
	List<TodoDto> findAllEnded(@PathVariable("idUtilisateur") Integer idUtilisateur);
	
	@GetMapping(value = APP_ROOT + "/todos/NotEnded/{idUtilisateur}")
	List<TodoDto> findAllNotEnded(@PathVariable("idUtilisateur") Integer idUtilisateur);
	
	@PostMapping(value = APP_ROOT + "/todo/corbeille/{idTodo}")
	void corbeille(@PathVariable("idTodo") Integer id);
	
	@PostMapping(value = APP_ROOT + "/todo/important/{idTodo}")
	void important(@PathVariable("idTodo") Integer id);
	
	@GetMapping(value = APP_ROOT + "/todos/findAllCorbeille/{idUtilisateur}")
	List<TodoDto> findAllCorbeille(@PathVariable("idUtilisateur") Integer id);
	
	@GetMapping(value = APP_ROOT + "/todos/findAllImportant/{idUtilisateur}")
	List<TodoDto> findAllImportant(@PathVariable("idUtilisateur") Integer id);
}

package com.todo.controller.api;


import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.todo.model.dto.UtilisateurDto;
import io.swagger.annotations.Api;
import static com.todo.utils.Constants.*;

@Api(UTILISATEUR_ENDPOINT)
public interface UtilisateurApi {

	@PostMapping(UTILISATEUR_ENDPOINT + "/create")
	UtilisateurDto save(@RequestBody UtilisateurDto dto);
	
	@GetMapping(UTILISATEUR_ENDPOINT + "/{idUtilisateur}")
	UtilisateurDto findById(@PathVariable("idUtilisateur") Integer id);
	
	@GetMapping(UTILISATEUR_ENDPOINT + "/all")
	List<UtilisateurDto> findAll();
	
	@DeleteMapping(UTILISATEUR_ENDPOINT + "/delete/{idUtilisateur}")
	void delete (@PathVariable("idUtilisateur") Integer id);
	
	@GetMapping(UTILISATEUR_ENDPOINT + "/email/{emailUtilisateur}")
	UtilisateurDto findByEmail(@PathVariable("emailUtilisateur") String email);
}

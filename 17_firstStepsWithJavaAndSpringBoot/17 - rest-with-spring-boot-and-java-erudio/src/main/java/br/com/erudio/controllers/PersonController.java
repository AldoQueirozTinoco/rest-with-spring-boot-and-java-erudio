package br.com.erudio.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.services.PersonServices;
import br.com.erudio.util.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

//A API Rest retorna dados em JSON, XML... Enquanto a Web retorna html, css...
//@O controle cria um map do Model Object e encontrar uma view equivalente
//o @restcontroler retorna o objeto e escreve suas informações direto no http
//@CrossOrigin
@RestController// Mistura do @ResponseBody com o @Controller
@RequestMapping("/api/person/v1")
@Tag(name = "People", description="Endpoints for managing People")
public class PersonController {
	
	@Autowired //Age em conjunt com @Service para injeção de dependencias
	private PersonServices service;
	//private PersonServices service = new PersonServices();
	
	@GetMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,MediaType.APPLICATION_YML})
	@Operation(summary = "Finds all people",description="Finds all People",
	tags= {"People"},
	responses = {
			@ApiResponse(description = "Success", responseCode = "200", 
					content = {
							@Content(
									mediaType = "application/json",
									array = @ArraySchema(schema = @Schema(implementation = PersonVO.class))
									)					
		}),
	 @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
	 @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
	 @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
	 @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
	 
	}
   )
	public List<PersonVO> findAll(){
		return service.findAll();
	}
	
	@CrossOrigin(origins = "http://localhost:8080")
	@GetMapping(value = "/{id}", produces ={MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,MediaType.APPLICATION_YML})
	@Operation(summary = "Finds a person",description="Finds a person",
	tags= {"People"},
	responses = {
	 @ApiResponse(description = "Success", responseCode = "200", 
		content =  @Content(schema = @Schema(implementation = PersonVO.class))
		 ),
	 @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
	 @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
	 @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
	 @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
	 @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
	 
	}
   )
	public PersonVO findById(
			@PathVariable(value="id") Long id){
		return service.findById(id);
	}
	
	//CRUD Create Read Update Delete
	@PutMapping(
			consumes ={MediaType.APPLICATION_JSON, 
					MediaType.APPLICATION_XML,
					MediaType.APPLICATION_YML},
			produces ={MediaType.APPLICATION_JSON, 
					MediaType.APPLICATION_XML,
					MediaType.APPLICATION_YML})
	@Operation(summary = "Updates a person",description="Updates a Person by passing in a JSON,XML or YML of the desired changes in its content",
	tags= {"People"},
	responses = {
	 @ApiResponse(description = "Updated", responseCode = "200", 
		content =  @Content(schema = @Schema(implementation = PersonVO.class))
		 ),
	 @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
	 @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
	 @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
	 @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
	 @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
	 
	}
   )
	public PersonVO update(@RequestBody PersonVO person){
		return service.update(person);
	}
	
	
	@CrossOrigin(origins = {"http://localhost:8080","https://erudio.com.br"})
	@PostMapping(
			consumes ={MediaType.APPLICATION_JSON, 
					MediaType.APPLICATION_XML,
					MediaType.APPLICATION_YML},
			produces ={MediaType.APPLICATION_JSON, 
					MediaType.APPLICATION_XML,
					MediaType.APPLICATION_YML})
	@Operation(summary = "Adds a person",description="Adds a person by passing in a JSON, XML or YML representation of its contents",
	tags= {"People"},
	responses = {
	 @ApiResponse(description = "Success", responseCode = "200", 
		content =  @Content(schema = @Schema(implementation = PersonVO.class))
		 ),
	 @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
	 @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
	 @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
	 
	}
   )
	public PersonVO create(@RequestBody PersonVO person){
		return service.create(person);
	}
	
	@DeleteMapping("/{id}")
	@Operation(summary = "Deletes a person",description="Deletes a person by passing in a JSON, XML or YML representation of its contents",
	tags= {"People"},
	responses = {
	 @ApiResponse(description = "No content", responseCode = "204", content =  @Content),
	 @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
	 @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
	 @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
	 @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
	 
	}
   )
	public ResponseEntity<?> delete(
			@PathVariable(value="id") Long id){
		 service.delete(id);
		 return ResponseEntity.noContent().build();
	}
	
	
	
	

	
}


package br.com.erudio.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.erudio.data.vo.v1.BookVO;
import br.com.erudio.services.BookServices;
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
@RestController// Mistura do @ResponseBody com o @Controller
@RequestMapping("/api/Book/v1")
@Tag(name = "Book", description="Endpoints for managing Book")
public class BookController {
	
	@Autowired //Age em conjunt com @Service para injeção de dependencias
	private BookServices service;
	//private BookServices service = new BookServices();
	
	@GetMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,MediaType.APPLICATION_YML})
	@Operation(summary = "Finds all Book",description="Finds all Book",
	tags= {"Book"},
	responses = {
			@ApiResponse(description = "Success", responseCode = "200", 
					content = {
							@Content(
									mediaType = "application/json",
									array = @ArraySchema(schema = @Schema(implementation = BookVO.class))
									)					
		}),
	 @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
	 @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
	 @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
	 @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
	 
	}
   )
	public List<BookVO> findAll(){
		return service.findAll();
	}
	
	@GetMapping(value = "/{id}", produces ={MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,MediaType.APPLICATION_YML})
	@Operation(summary = "Finds a Book",description="Finds a Book",
	tags= {"Book"},
	responses = {
	 @ApiResponse(description = "Success", responseCode = "200", 
		content =  @Content(schema = @Schema(implementation = BookVO.class))
		 ),
	 @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
	 @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
	 @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
	 @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
	 @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
	 
	}
   )
	public BookVO findById(
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
	@Operation(summary = "Updates a Book",description="Updates a Book by passing in a JSON,XML or YML of the desired changes in its content",
	tags= {"Book"},
	responses = {
	 @ApiResponse(description = "Updated", responseCode = "200", 
		content =  @Content(schema = @Schema(implementation = BookVO.class))
		 ),
	 @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
	 @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
	 @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
	 @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
	 @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
	 
	}
   )
	public BookVO update(@RequestBody BookVO Book){
		return service.update(Book);
	}
	@PostMapping(
			consumes ={MediaType.APPLICATION_JSON, 
					MediaType.APPLICATION_XML,
					MediaType.APPLICATION_YML},
			produces ={MediaType.APPLICATION_JSON, 
					MediaType.APPLICATION_XML,
					MediaType.APPLICATION_YML})
	@Operation(summary = "Adds a Book",description="Adds a Book by passing in a JSON, XML or YML representation of its contents",
	tags= {"Book"},
	responses = {
	 @ApiResponse(description = "Success", responseCode = "200", 
		content =  @Content(schema = @Schema(implementation = BookVO.class))
		 ),
	 @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
	 @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
	 @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
	 
	}
   )
	public BookVO create(@RequestBody BookVO Book){
		return service.create(Book);
	}
	
	@DeleteMapping("/{id}")
	@Operation(summary = "Deletes a Book",description="Deletes a Book by passing in a JSON, XML or YML representation of its contents",
	tags= {"Book"},
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


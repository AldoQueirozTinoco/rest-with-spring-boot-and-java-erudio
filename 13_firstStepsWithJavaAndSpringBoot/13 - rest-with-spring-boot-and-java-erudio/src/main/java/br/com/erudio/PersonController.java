package br.com.erudio;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.services.PersonServices;
import br.com.erudio.util.MediaType;

//A API Rest retorna dados em JSON, XML... Enquanto a Web retorna html, css...
//@O controle cria um map do Model Object e encontrar uma view equivalente
//o @restcontroler retorna o objeto e escreve suas informações direto no http
@RestController// Mistura do @ResponseBody com o @Controller
@RequestMapping("/api/person/v1")
public class PersonController {
	
	@Autowired //Age em conjunt com @Service para injeção de dependencias
	private PersonServices service;
	//private PersonServices service = new PersonServices();
	
	@GetMapping(produces = { MediaType.APPLICATION_JSON, 
			MediaType.APPLICATION_XML,
			MediaType.APPLICATION_YML})
	public List<PersonVO> findAll(){
		return service.findAll();
	}
	
	@GetMapping(value = "/{id}",
			produces ={MediaType.APPLICATION_JSON, 
					MediaType.APPLICATION_XML,
					MediaType.APPLICATION_YML})
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
	
	public PersonVO update(@RequestBody PersonVO person){
		return service.update(person);
	}
	@PostMapping(
			consumes ={MediaType.APPLICATION_JSON, 
					MediaType.APPLICATION_XML,
					MediaType.APPLICATION_YML},
			produces ={MediaType.APPLICATION_JSON, 
					MediaType.APPLICATION_XML,
					MediaType.APPLICATION_YML})
	
	public PersonVO create(@RequestBody PersonVO person){
		return service.create(person);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(
			@PathVariable(value="id") Long id){
		 service.delete(id);
		 return ResponseEntity.noContent().build();
	}
	
	
	
	

	
}


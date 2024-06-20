package br.com.erudio;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.model.Person;
import br.com.erudio.services.PersonServices;

//A API Rest retorna dados em JSON, XML... Enquanto a Web retorna html, css...
//@O controle cria um map do Model Object e encontrar uma view equivalente
//o @restcontroler retorna o objeto e escreve suas informações direto no http
@RestController// Mistura do @ResponseBody com o @Controller
@RequestMapping("/person")
public class PersonController {
	
	@Autowired //Age em conjunt com @Service para injeção de dependencias
	private PersonServices service;
	//private PersonServices service = new PersonServices();
	
	@RequestMapping(method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Person> findAll(){
		return service.findAll();
	}
	
	
	//CRUD Create Read Update Delete
	@RequestMapping(
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	
	public Person update(@RequestBody Person person){
		return service.update(person);
	}
	@RequestMapping(
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	
	public Person create(@RequestBody Person person){
		return service.create(person);
	}
	
	@RequestMapping(value = "/{id}",
			method = RequestMethod.DELETE)
	public void delete(
			@PathVariable(value="id") String id){
		 service.delete(id);
	}
	@RequestMapping(value = "/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Person findById(
			@PathVariable(value="id") String id){
			return service.findById(id);
		}
	
	
	

	
}


package br.com.erudio;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//A API Rest retorna dados em JSON, XML... Enquanto a Web retorna html, css...
//@O controle cria um map do Model Object e encontrar uma view equivalente
//o @restcontroler retorna o objeto e escreve suas informações direto no http
@RestController// Mistura do @ResponseBody com o @Controller
public class GreetingController {
	
	private static final String template = "Hello %s!";
	private final AtomicLong  counter = new AtomicLong();

	@RequestMapping("/greeting")
	public Greeting greeting(@RequestParam(value="name",defaultValue="World")
								String name) {
	return new Greeting(counter.incrementAndGet(),String.format(template, name));
	}
	
}


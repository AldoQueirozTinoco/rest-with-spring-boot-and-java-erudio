package br.com.erudio;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.exceptions.UnsupportedMathOperationException;

//A API Rest retorna dados em JSON, XML... Enquanto a Web retorna html, css...
//@O controle cria um map do Model Object e encontrar uma view equivalente
//o @restcontroler retorna o objeto e escreve suas informações direto no http
@RestController// Mistura do @ResponseBody com o @Controller
public class MathController extends Checker {
	

	@GetMapping("/sum/{numberOne}/{numberTwo}")
	public Double sum(
			@PathVariable(value="numberOne") String numberOne,
			@PathVariable(value="numberTwo") String numberTwo
			)throws Exception{
		
		if(!isNumeric(numberOne) || !isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value");
		}
		
		return convertToDouble(numberOne) + convertToDouble(numberTwo);	
		}
	
	@GetMapping("/sub/{numberOne}/{numberTwo}")
	public Double sub(
			@PathVariable(value="numberOne") String numberOne,
			@PathVariable(value="numberTwo") String numberTwo
			)throws Exception{
		if(!isNumeric(numberOne) || !isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value");
		}
		
		return convertToDouble(numberOne) - convertToDouble(numberTwo);
	}
	
	@GetMapping("/multi/{numberOne}/{numberTwo}")
	public Double multi(
			@PathVariable(value="numberOne") String numberOne,
			@PathVariable(value="numberTwo") String numberTwo
			)throws Exception{
		if(!isNumeric(numberOne) || !isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value");
		}
		
		return convertToDouble(numberOne) * convertToDouble(numberTwo);
	}
	@GetMapping("/div/{numberOne}/{numberTwo}")
	public Double div(
			@PathVariable(value="numberOne") String numberOne,
			@PathVariable(value="numberTwo") String numberTwo
			)throws Exception{
		if(!isNumeric(numberOne) || !isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value");
		}
		
		return convertToDouble(numberOne) / convertToDouble(numberTwo);
	}
	
	@GetMapping("/ave/{numberOne}/{numberTwo}")
	public Double ave(
			@PathVariable(value="numberOne") String numberOne,
			@PathVariable(value="numberTwo") String numberTwo
			)throws Exception{
		if(!isNumeric(numberOne) || !isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value");
		}
		
		return (convertToDouble(numberOne) + convertToDouble(numberTwo))/2;
	}
	
	@GetMapping("/sqrt/{numberOne}")
	public Double sqrt(
			@PathVariable(value="numberOne") String numberOne
			)throws Exception{
		if(!isNumeric(numberOne)) {
			throw new UnsupportedMathOperationException("Please set a numeric value");
		}
		
		return Math.sqrt(convertToDouble(numberOne)) ;
	}
	

	
}


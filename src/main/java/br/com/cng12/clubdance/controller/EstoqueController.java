package br.com.cng12.clubdance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EstoqueController {
	
	private static final String INICIO_ESTOQUE = "/estoque/inicio-estoque";

	@GetMapping(INICIO_ESTOQUE)
	public String inicioEstoque() {
		
		return "estoque/inicio-estoque";
	}	
}

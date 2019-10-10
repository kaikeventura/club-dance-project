package br.com.cng12.clubdance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EstoqueController {

	@GetMapping("/estoque/inicio-estoque")
	public String inicioEstoque() {
		return "estoque/inicio-estoque";
	}
	
}

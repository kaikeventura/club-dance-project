package br.com.cng12.clubdance.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EstoqueController {
	
	private static final String INICIO_ESTOQUE = "/estoque/inicio-estoque";

	@GetMapping(INICIO_ESTOQUE)
	public String inicioEstoque(ModelMap model) {
		
		model.addAttribute("username", SecurityContextHolder.getContext()
		        .getAuthentication().getName());
		
		return "estoque/inicio-estoque";
	}	
}

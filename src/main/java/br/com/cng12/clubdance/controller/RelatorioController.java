package br.com.cng12.clubdance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RelatorioController {

	private static final String INICIO_RELATORIO = "/relatorio/inicio";
	
	@GetMapping(INICIO_RELATORIO)
	public String inicioRelatorios() {
		
		return "relatorio/relatorios";
	}
}

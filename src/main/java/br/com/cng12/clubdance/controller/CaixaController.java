package br.com.cng12.clubdance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CaixaController {

	@GetMapping("/caixa/inicio-caixa")
	public String paginaInicialDoCaixa() {
		
		return "caixa/inicio-caixa";
	}
}

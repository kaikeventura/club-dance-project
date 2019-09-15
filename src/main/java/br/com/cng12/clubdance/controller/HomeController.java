package br.com.cng12.clubdance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	//Chama a página para renderizar
	@GetMapping("/")
	public String paginaInicial() {
		return "index";
	}
	
}

package br.com.cng12.clubdance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String paginaInicial() {
		return "index";
	}
	
	/*
	 * @GetMapping("/teste") public String preTeste(Nf nf) {
	 * 
	 * return "teste"; }
	 * 
	 * @PostMapping("/teste") public String teste(@Valid Nf nf) {
	 * 
	 * List<Nf> prod = Arrays.asList(nf); service.salvar(nf);
	 * 
	 * System.out.println(prod.toString());
	 * 
	 * return "redirect:/teste"; }
	 */
	
}

package br.com.cng12.clubdance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SimuladorMaquininhaCartaoController {

	@GetMapping("/cielo/cielo-pagamentos")
	public String cielo() {
		
		return "cielo/cielo";
	}
}

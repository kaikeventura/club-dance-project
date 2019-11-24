package br.com.cng12.clubdance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SessaoController {
	
	private static final String LOGOUT = "/logout";

	@GetMapping(LOGOUT)
	public String logout() {
		
		return "/";
	}
}

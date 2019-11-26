package br.com.cng12.clubdance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SessaoController {
	
	private static final String LOGOUT = "/logout";
	private static final String ACCESS_DENIED = "/access-denied";

	@GetMapping(LOGOUT)
	public String logout() {
		
		return "/";
	}
	
	@GetMapping(ACCESS_DENIED)
	public String acessoNegado() {
		
		return "error";
	}
}

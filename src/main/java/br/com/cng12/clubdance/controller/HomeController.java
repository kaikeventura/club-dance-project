package br.com.cng12.clubdance.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	
	private static final String HOME = "/";
	
//	@GetMapping(HOME)
//	public String paginaInicial() {
//		
//		ModelAndView modelAndView = new ModelAndView();
//		
//		modelAndView.addObject("username", SecurityContextHolder.getContext()
//		        .getAuthentication().getName());
//		
//		return "index";
//	}
	
	@GetMapping(HOME)
	public ModelAndView paginaInicial() {
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("username", SecurityContextHolder.getContext()
		        .getAuthentication().getName());
		
		modelAndView.setViewName("index");
		
		return modelAndView;
	}
	
}

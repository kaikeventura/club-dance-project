package br.com.cng12.clubdance.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	
	private static final String HOME = "/";
	
	@GetMapping(HOME)
	public String paginaInicial(ModelMap model) {
		
		model.addAttribute("username", SecurityContextHolder.getContext()
		        .getAuthentication().getName());
		
		return "index";
	}
	
	@GetMapping("/login")
	public ModelAndView login() {
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		
		return modelAndView;
	}
	
}

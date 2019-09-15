package br.com.cng12.clubdance.controller;

import java.text.ParseException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.cng12.clubdance.entity.EventoEntity;
import br.com.cng12.clubdance.service.impl.EventoServiceImpl;

@Controller
public class EventoController {

	// Injeção de dependência
	@Autowired
	private EventoServiceImpl service;

	// Chama a página para renderizar
//	@GetMapping("/evento/eventos")
//	public String eventos() {
//		return "evento/eventos";
//	}

	// Chama a página para renderizar
	@GetMapping("/evento/cadastrar-evento")
	public String cadastrarEvento(EventoEntity eventoEntity) {
		return "evento/cadastrar-evento";
	}

	@PostMapping("/evento/cadastrar-evento")
	public String salvarEvento(@Valid EventoEntity eventoEntity) {

		service.salvar(eventoEntity);
		
		return "redirect:/evento/cadastrar-evento";
	}

	@GetMapping("/evento/eventos")
	public String listarEventos(ModelMap model) {

		model.addAttribute("eventos", service.listar());

		return "evento/eventos";
	}
	
	public static void main(String[] args) throws ParseException {
		
	}

}

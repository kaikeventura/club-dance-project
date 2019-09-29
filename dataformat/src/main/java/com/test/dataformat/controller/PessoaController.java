package com.test.dataformat.controller;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.dataformat.model.Pessoa;

@Controller
@RequestMapping("/")
public class PessoaController {

	public List<Pessoa> pessoas() {
		List<Pessoa> pessoas = Arrays.asList(new Pessoa(1, "Kaike", LocalDate.now()),
				new Pessoa(1, "Amanda", LocalDate.now()), new Pessoa(1, "Teste", LocalDate.of(2010, 4, 24)));
		return pessoas;
	}

	@GetMapping("/")
	public String index(Pessoa pessoa, Model model) {

		model.addAttribute("pessoas", pessoas());

		return "index";
	}
}

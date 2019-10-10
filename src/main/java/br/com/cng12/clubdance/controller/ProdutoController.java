package br.com.cng12.clubdance.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.cng12.clubdance.entity.ProdutoEntity;
import br.com.cng12.clubdance.service.impl.ProdutoServiceImpl;

@Controller
public class ProdutoController {
	
	@Autowired
	private ProdutoServiceImpl produtoService;

	@GetMapping("/estoque/produto/cadastrar-produto")
	public String cadastroDeProduto(ProdutoEntity produtoEntity) {
		
		return "estoque/produto/cadastrar-produto";
	}
	
	@PostMapping("/estoque/produto/cadastrar-produto")
	public String salvarProduto(@Valid ProdutoEntity produtoEntity) {
		
		produtoService.salvar(produtoEntity);
		
		return "redirect:/estoque/produto/cadastrar-produto";
	}
	
	@GetMapping("/estoque/produto/produtos")
	public String listarEventos(ModelMap model) {

		model.addAttribute("produtos", produtoService.listar());

		return "estoque/produto/produtos";
	}
}

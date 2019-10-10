package br.com.cng12.clubdance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.cng12.clubdance.service.impl.ProdutoServiceImpl;

@Controller
public class EstoqueController {

	@Autowired
	private ProdutoServiceImpl produtoService;
	
	@GetMapping("/estoque/inicio-estoque")
	public String inicioEstoque() {
		return "estoque/inicio-estoque";
	}
	
	@GetMapping("/estoque/produto/produtos")
	public String listarProdutos() {
		return "estoque/produto/produtos";
	}
}

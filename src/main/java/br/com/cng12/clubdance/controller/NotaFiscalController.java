package br.com.cng12.clubdance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.cng12.clubdance.entity.FornecedorEntity;
import br.com.cng12.clubdance.service.impl.FornecedorServiceImpl;

@Controller
public class NotaFiscalController {

	@Autowired
	private FornecedorServiceImpl fornecedorService;
	
	@GetMapping("/estoque/nota-fiscal/lancar-nota-fiscal")
	public String lancarNotaFiscal(FornecedorEntity fornecedorEntity, ModelMap modelMap) {
		
		modelMap.addAttribute("fornecedores", fornecedorService.listar());
		
		return "estoque/nota-fiscal/lancar-nota-fiscal";
	}
	
	@GetMapping("/estoque/nota-fiscal/buscar-fornecedor/nome")
	public String buscarFornecedorPorNome(@RequestParam("nome") String nome, ModelMap model) {
		model.addAttribute("fornecedores", fornecedorService.buscarPorNome(nome));
		
		return "estoque/nota-fiscal/lancar-nota-fiscal";
	}
}

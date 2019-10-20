package br.com.cng12.clubdance.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.cng12.clubdance.entity.ProdutoEntity;
import br.com.cng12.clubdance.service.impl.ProdutoServiceImpl;
import br.com.cng12.clubdance.utils.Status;
import br.com.cng12.clubdance.utils.UnidadeMedida;

@Controller
public class ProdutoController {

	@Autowired
	private ProdutoServiceImpl produtoService;

	@GetMapping("/estoque/produto/cadastrar-produto")
	public String cadastroDeProduto(ProdutoEntity produtoEntity) {

		return "estoque/produto/cadastrar-produto";
	}

	@PostMapping("/estoque/produto/cadastrar-produto")
	public String salvarProduto(@Valid ProdutoEntity produtoEntity, RedirectAttributes attr) {

		produtoEntity.setQtdeEstoque(0);
		produtoEntity.setPreco(0.0D);
		produtoService.salvar(produtoEntity);
		attr.addFlashAttribute("success", "Salvo com sucesso.");

		return "redirect:/estoque/produto/cadastrar-produto";
	}

	@GetMapping("/estoque/produto/produtos")
	public String listarProdutos(ModelMap model) {

		model.addAttribute("produtos", produtoService.listar());

		return "estoque/produto/produtos";
	}

	@GetMapping("/estoque/produto/editar-produto/{id}")
	public String preEditarProduto(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("produtoEntity", produtoService.buscarPorId(id));
		return "estoque/produto/cadastrar-produto";
	}

	@PostMapping("/estoque/produto/editar-produto")
	public String editarProduto(@Valid ProdutoEntity produtoEntity, RedirectAttributes attr) {
		
		produtoService.editar(produtoEntity.getNome(), produtoEntity.getMarca(), produtoEntity.getUnidadeMedida(),
				produtoEntity.getMargemLucro(), produtoEntity.getStatus(), produtoEntity.getId());
		attr.addFlashAttribute("success", "Salvo com sucesso.");
		return "redirect:/estoque/produto/cadastrar-produto";
	}

	@GetMapping("/estoque/produto/excluir-produto/{id}")
	public String excluirProduto(@PathVariable("id") Long id) {

		produtoService.excluir(id);

		return "redirect:/estoque/produto/produtos";
	}

	@ModelAttribute("unmd")
	public UnidadeMedida[] getUNMDs() {
		return UnidadeMedida.values();
	}

	@ModelAttribute("status")
	public Status[] getStatus() {
		return Status.values();
	}

	@GetMapping("/estoque/produto/buscar/nome")
	public String buscarProdutoPorNome(@RequestParam("nome") String nome, ModelMap model) {
		model.addAttribute("produtos", produtoService.buscarPorNome(nome));
		return "estoque/produto/produtos";
	}
}

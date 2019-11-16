package br.com.cng12.clubdance.controller;

import java.util.List;

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

import br.com.cng12.clubdance.entity.ComandaProdutoEntity;
import br.com.cng12.clubdance.entity.NotaFiscalFornecedorProdutoEntity;
import br.com.cng12.clubdance.entity.ProdutoEntity;
import br.com.cng12.clubdance.service.impl.ComandaProdutoServiceImpl;
import br.com.cng12.clubdance.service.impl.NotaFiscalFornecedorProdutoEntityServiceImpl;
import br.com.cng12.clubdance.service.impl.ProdutoServiceImpl;
import br.com.cng12.clubdance.utils.Status;
import br.com.cng12.clubdance.utils.UnidadeMedida;

@Controller
public class ProdutoController {
	
	private static final String CADASTRO_DE_PRODUTO = "/estoque/produto/cadastrar-produto";
	private static final String SALVAR_PRODUTO = "/estoque/produto/cadastrar-produto";
	private static final String LISTAR_PRODUTOS = "/estoque/produto/produtos";
	private static final String PRE_EDITAR_PRODUTO = "/estoque/produto/editar-produto/{id}";
	private static final String EDITAR_PRODUTO = "/estoque/produto/editar-produto";
	private static final String EXCLUIR_PRODUTO = "/estoque/produto/excluir-produto/{id}";
	private static final String BUSCAR_PRODUTO_POR_NOME = "/estoque/produto/buscar/nome";

	@Autowired
	private ProdutoServiceImpl produtoService;

	@Autowired
	private ComandaProdutoServiceImpl comandaProdutoService;

	@Autowired
	private NotaFiscalFornecedorProdutoEntityServiceImpl NFPService;

	@GetMapping(CADASTRO_DE_PRODUTO)
	public String cadastroDeProduto(ProdutoEntity produtoEntity) {

		return "estoque/produto/cadastrar-produto";
	}

	@PostMapping(SALVAR_PRODUTO)
	public String salvarProduto(@Valid ProdutoEntity produtoEntity, RedirectAttributes attr) {

		produtoEntity.setQtdeEstoque(0);
		produtoEntity.setPreco(0.0D);
		produtoService.salvar(produtoEntity);
		attr.addFlashAttribute("success", "Salvo com sucesso.");

		return "redirect:/estoque/produto/cadastrar-produto";
	}

	@GetMapping(LISTAR_PRODUTOS)
	public String listarProdutos(ModelMap model) {

		model.addAttribute("produtos", produtoService.listar());

		return "estoque/produto/produtos";
	}

	@GetMapping(PRE_EDITAR_PRODUTO)
	public String preEditarProduto(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("produtoEntity", produtoService.buscarPorId(id));
		return "estoque/produto/cadastrar-produto";
	}

	@PostMapping(EDITAR_PRODUTO)
	public String editarProduto(@Valid ProdutoEntity produtoEntity, RedirectAttributes attr) {

		produtoService.editar(produtoEntity.getNome(), produtoEntity.getMarca(), produtoEntity.getUnidadeMedida(),
				produtoEntity.getMargemLucro(), produtoEntity.getStatus(), produtoEntity.getId());
		attr.addFlashAttribute("success", "Editado com sucesso.");
		return "redirect:/estoque/produto/cadastrar-produto";
	}

	@GetMapping(EXCLUIR_PRODUTO)
	public String excluirProduto(@PathVariable("id") Long id, RedirectAttributes attr) {

		ProdutoEntity produtoEntity = produtoService.buscarPorId(id);
		List<ComandaProdutoEntity> comandaProdutoEntitys = comandaProdutoService
				.buscarComandasQuePossuemProdutosVinculados(produtoEntity);
		List<NotaFiscalFornecedorProdutoEntity> notaFiscalFornecedorProdutoEntitys = NFPService
				.buscarNotasFiscaisQuePossuemProdutosVinculados(produtoEntity);

		if (!notaFiscalFornecedorProdutoEntitys.isEmpty()) {
			attr.addFlashAttribute("error", "O produto " + produtoEntity.getNome()
					+ " não pode ser excluído, pois está vinculado com outras partes do sistema.");
			return "redirect:/estoque/produto/produtos";
		} 
		else if (!comandaProdutoEntitys.isEmpty()) {
			attr.addFlashAttribute("error", "O produto " + produtoEntity.getNome()
					+ " não pode ser excluído, pois está vinculado com outras partes do sistema.");
			return "redirect:/estoque/produto/produtos";
		} 
		else {
			produtoService.excluir(id);
			attr.addFlashAttribute("success", "O produto " + produtoEntity.getNome() + " foi excluído com sucesso.");
			return "redirect:/estoque/produto/produtos";
		}

	}

	@ModelAttribute("unmd")
	public UnidadeMedida[] getUNMDs() {
		return UnidadeMedida.values();
	}

	@ModelAttribute("status")
	public Status[] getStatus() {
		return Status.values();
	}

	@GetMapping(BUSCAR_PRODUTO_POR_NOME)
	public String buscarProdutoPorNome(@RequestParam("nome") String nome, ModelMap model) {
		model.addAttribute("produtos", produtoService.buscarPorNome(nome));
		return "estoque/produto/produtos";
	}
}

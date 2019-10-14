package br.com.cng12.clubdance.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.cng12.clubdance.entity.FornecedorEntity;
import br.com.cng12.clubdance.entity.NotaFiscalEntity;
import br.com.cng12.clubdance.entity.NotaFiscalFornecedorProdutoEntity;
import br.com.cng12.clubdance.entity.ProdutoEntity;
import br.com.cng12.clubdance.service.impl.FornecedorServiceImpl;
import br.com.cng12.clubdance.service.impl.NotaFiscalServiceImpl;
import br.com.cng12.clubdance.service.impl.ProdutoServiceImpl;
import br.com.cng12.clubdance.utils.Temporario;

@Controller
public class NotaFiscalController {

	@Autowired
	private FornecedorServiceImpl fornecedorService;

	@Autowired
	private Temporario temp;

	@Autowired
	private NotaFiscalServiceImpl notaFiscalService;

	@Autowired
	private ProdutoServiceImpl produtoService;

	@GetMapping("/estoque/nota-fiscal/selecionar-fornecedor")
	public String selecionarFornecedor(FornecedorEntity fornecedorEntity, ModelMap modelMap) {

		modelMap.addAttribute("fornecedores", fornecedorService.listarFornecedoresAtivos());

		return "estoque/nota-fiscal/selecionar-fornecedor";
	}

	@GetMapping("/estoque/nota-fiscal/buscar-fornecedor/nome")
	public String buscarFornecedorPorNome(@RequestParam("nome") String nome, ModelMap model) {
		model.addAttribute("fornecedores", fornecedorService.buscarPorNome(nome));

		return "estoque/nota-fiscal/selecionar-fornecedor";
	}

	@GetMapping("/estoque/nota-fiscal/lancar/nota-fiscal/{id}")
	public String preNotaFiscal(@PathVariable("id") Long id, ModelMap model, NotaFiscalEntity notaFiscalEntity) {

		model.addAttribute("fornecedorEntity", fornecedorService.buscarPorId(id));
		temp.setIdFornecedorTemp(id);
		System.out.println("Fornecedor: " + temp.getIdFornecedorTemp());
		System.out.println("NotaFiscal: " + temp.getIdNotaFiscalTemp());

		return "estoque/nota-fiscal/lancar/nota-fiscal";
	}

	@PostMapping("/estoque/nota-fiscal/lancar/nota-fiscal")
	public String novaNotaFiscal(@Valid NotaFiscalEntity notaFiscalEntity) {

		notaFiscalService.salvar(notaFiscalEntity);

		temp.setIdNotaFiscalTemp(notaFiscalEntity.getId());
		System.out.println("Fornecedor: " + temp.getIdFornecedorTemp());
		System.out.println("NotaFiscal: " + temp.getIdNotaFiscalTemp());

		return "redirect:/estoque/nota-fiscal/lancar/lancar-produto";
	}

	@GetMapping("/estoque/nota-fiscal/lancar/lancar-produto")
	public String preLancarProduto(FornecedorEntity fornecedorEntity, NotaFiscalEntity notaFiscalEntity,
			ProdutoEntity produtoEntity, ModelMap modelMap) {

		modelMap.addAttribute("fornecedorEntity", fornecedorService.buscarPorId(temp.getIdFornecedorTemp()));
		modelMap.addAttribute("notaFiscalEntity", notaFiscalService.buscarPorId(temp.getIdNotaFiscalTemp()));
		modelMap.addAttribute("produtos", produtoService.listarProdutosAtivos());

		return "/estoque/nota-fiscal/lancar/lancar-produto";
	}

	@PostMapping("/estoque/nota-fiscal/lancar/lancar-produto")
	public String lancarProduto(@Valid ProdutoEntity produtoEntity,
			@Valid NotaFiscalFornecedorProdutoEntity notaFiscalFornecedorProdutoEntity) {

		return "/estoque/nota-fiscal/lancar/lancar-produto";
	}

//	@PostMapping("/teste")
//	public String test(@Valid NotaFiscalEntity notaFiscalEntity) {
//		
//		notaFiscalService.salvar(notaFiscalEntity);
//		
//		temp.setIdNotaFiscalTemp(notaFiscalEntity.getId());
//		System.out.println("preNotaFiscal: " + temp.getIdFornecedorTemp());
//		System.out.println("novaNotaFiscal: " + temp.getIdNotaFiscalTemp());
//		
//		return "estoque/nota-fiscal/lancar/lancar-produto";
//	}

}

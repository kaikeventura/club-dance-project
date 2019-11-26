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

import br.com.cng12.clubdance.entity.FornecedorEntity;
import br.com.cng12.clubdance.service.impl.FornecedorServiceImpl;
import br.com.cng12.clubdance.utils.Atividades;
import br.com.cng12.clubdance.utils.Status;
import br.com.cng12.clubdance.utils.UF;

@Controller
public class FornecedorController {
	
	private static final String CADASTRO_DE_PRODUTO = "/estoque/fornecedor/cadastrar-fornecedor";
	private static final String SALVAR_FORNECEDOR = "/estoque/fornecedor/cadastrar-fornecedor";
	private static final String LISTAR_FORNECEDORES = "/estoque/fornecedor/fornecedores";
	private static final String DETALHES_FORNECEDOR = "/estoque/fornecedor/detalhes/fornecedor-detalhes/{id}";
	private static final String PRE_EDITAR_FORNECEDOR = "/estoque/fornecedor/detalhes/fornecedor-detalhes/editar/{id}";
	private static final String EDITAR_FORNECEDOR = "/estoque/fornecedor/detalhes/fornecedor-detalhes/editar-fornecedor";
	private static final String EXCLUIR_FORNECEDOR = "/estoque/fornecedor/detalhes/fornecedor-detalhes/excluir/{id}";
	private static final String BUSCAR_FORNECEDOR_POR_NOME = "/estoque/fornecedor/buscar/nome";

	@Autowired
	private FornecedorServiceImpl fornecedorService;

	@GetMapping(CADASTRO_DE_PRODUTO)
	public String cadastroDeProduto(FornecedorEntity fornecedorEntity) {

		return "estoque/fornecedor/cadastrar-fornecedor";
	}

	@PostMapping(SALVAR_FORNECEDOR)
	public String salvarFornecedor(@Valid FornecedorEntity fornecedorEntity, RedirectAttributes attr) {

		fornecedorService.salvar(fornecedorEntity);
		attr.addFlashAttribute("success", "Salvo com sucesso.");
		
		return "redirect:/estoque/fornecedor/cadastrar-fornecedor";
	}

	@GetMapping(LISTAR_FORNECEDORES)
	public String listarFornecedores(ModelMap model) {

		model.addAttribute("fornecedores", fornecedorService.listar());

		return "estoque/fornecedor/fornecedores";
	}

	@GetMapping(DETALHES_FORNECEDOR)
	public String detalhesFornecedor(@PathVariable("id") Long id, ModelMap model) {

		model.addAttribute("fornecedorEntity", fornecedorService.buscarPorId(id));

		return "estoque/fornecedor/detalhes/fornecedor-detalhes";
	}

	@GetMapping(PRE_EDITAR_FORNECEDOR)
	public String preEditarFornecedor(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("fornecedorEntity", fornecedorService.buscarPorId(id));
		return "estoque/fornecedor/cadastrar-fornecedor";
	}

	@PostMapping(EDITAR_FORNECEDOR)
	public String editarFornecedor(@Valid FornecedorEntity fornecedorEntity, RedirectAttributes attr) {

		fornecedorService.editar(fornecedorEntity.getNomeFantasia(), fornecedorEntity.getRazaoSocial(), fornecedorEntity.getCnpj(),
				fornecedorEntity.getInscricaoEstadual(), fornecedorEntity.getEndereco(), fornecedorEntity.getBairro(),
				fornecedorEntity.getCidade(), fornecedorEntity.getUf(), fornecedorEntity.getCep(),
				fornecedorEntity.getTelefone(), fornecedorEntity.getCelular(), fornecedorEntity.getEmail(),
				fornecedorEntity.getNomeContato(), fornecedorEntity.getStatus(), fornecedorEntity.getId());
		attr.addFlashAttribute("success", "Editado com sucesso.");

		return "redirect:/estoque/fornecedor/cadastrar-fornecedor";
	}

	@GetMapping(EXCLUIR_FORNECEDOR)
	public String excluirFornecedor(@PathVariable("id") Long id) {

		fornecedorService.excluir(id);

		return "redirect:/estoque/fornecedor/fornecedores";
	}

	@ModelAttribute("uf")
	public UF[] getUFs() {
		return UF.values();
	}

	@ModelAttribute("status")
	public Status[] getStatus() {
		return Status.values();
	}

	@GetMapping(BUSCAR_FORNECEDOR_POR_NOME)
	public String buscarFornecedorPorNome(@RequestParam("nome") String nome, ModelMap model) {
		model.addAttribute("fornecedores", fornecedorService.buscarPorNome(nome));
		
		return "estoque/fornecedor/fornecedores";
	}
}

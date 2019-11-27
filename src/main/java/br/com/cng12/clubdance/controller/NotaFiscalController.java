package br.com.cng12.clubdance.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.cng12.clubdance.entity.FornecedorEntity;
import br.com.cng12.clubdance.entity.NotaFiscalEntity;
import br.com.cng12.clubdance.entity.NotaFiscalFornecedorProdutoEntity;
import br.com.cng12.clubdance.entity.ProdutoEntity;
import br.com.cng12.clubdance.service.impl.FornecedorServiceImpl;
import br.com.cng12.clubdance.service.impl.NotaFiscalFornecedorProdutoEntityServiceImpl;
import br.com.cng12.clubdance.service.impl.NotaFiscalServiceImpl;
import br.com.cng12.clubdance.service.impl.ProdutoServiceImpl;
import br.com.cng12.clubdance.utils.components.EntradaDeProdutoComponent;
import br.com.cng12.clubdance.utils.components.TemporarioComponent;
import br.com.cng12.clubdance.utils.dto.NotaFiscalAux;

@Controller
public class NotaFiscalController {
	
	private static final String SELECIONAR_FORNECEDOR = "/estoque/nota-fiscal/selecionar-fornecedor";
	private static final String BUSCAR_FORNECEDOR_POR_NOME = "/estoque/nota-fiscal/buscar-fornecedor/nome";
	private static final String PRE_NOTA_FISCAL = "/estoque/nota-fiscal/lancar/nota-fiscal/{id}";
	private static final String NOVA_NOTA_FISCAL = "/estoque/nota-fiscal/lancar/nota-fiscal";
	private static final String PRE_LANCAR_NOTA_FISCAL = "/estoque/nota-fiscal/lancar/lancar-produto";
	private static final String LANCAR_PRODUTO = "/estoque/nota-fiscal/lancar/lancar-produto";
	private static final String NOTAS_FISCAIS_LANCADAS = "/estoque/nota-fiscal/lista/notas-fiscais";
	private static final String PRE_EDITAR_NOTA_FISCAL_SELECIONADA = "/estoque/nota-fiscal/lista/editar/selecionar-produto/{id}";

	@Autowired
	private FornecedorServiceImpl fornecedorService;

	@Autowired
	private TemporarioComponent temp;

	@Autowired
	private NotaFiscalServiceImpl notaFiscalService;

	@Autowired
	private ProdutoServiceImpl produtoService;

	@Autowired
	private EntradaDeProdutoComponent entradaDeProdutoComponent;

	@Autowired
	private NotaFiscalFornecedorProdutoEntityServiceImpl NFPService;

	@GetMapping(SELECIONAR_FORNECEDOR)
	public String selecionarFornecedor(FornecedorEntity fornecedorEntity, ModelMap modelMap) {

		modelMap.addAttribute("username", SecurityContextHolder.getContext()
		        .getAuthentication().getName());
		
		modelMap.addAttribute("fornecedores", fornecedorService.listarFornecedoresAtivos());

		return "estoque/nota-fiscal/selecionar-fornecedor";
	}

	@GetMapping(BUSCAR_FORNECEDOR_POR_NOME)
	public String buscarFornecedorPorNome(@RequestParam("nome") String nome, ModelMap model) {
		
		model.addAttribute("username", SecurityContextHolder.getContext()
		        .getAuthentication().getName());
		
		model.addAttribute("fornecedores", fornecedorService.buscarPorNome(nome));

		return "estoque/nota-fiscal/selecionar-fornecedor";
	}

	@GetMapping(PRE_NOTA_FISCAL)
	public String preNotaFiscal(@PathVariable("id") Long id, ModelMap model, NotaFiscalEntity notaFiscalEntity) {

		model.addAttribute("username", SecurityContextHolder.getContext()
		        .getAuthentication().getName());
		
		model.addAttribute("fornecedorEntity", fornecedorService.buscarPorId(id));
		temp.setIdFornecedorTemp(id);

		return "estoque/nota-fiscal/lancar/nota-fiscal";
	}

	@PostMapping(NOVA_NOTA_FISCAL)
	public String novaNotaFiscal(@Valid NotaFiscalEntity notaFiscalEntity, ModelMap model, RedirectAttributes attr) {

		model.addAttribute("username", SecurityContextHolder.getContext()
		        .getAuthentication().getName());
		
		List<NotaFiscalEntity> notas = notaFiscalService
				.buscarPorNumero(notaFiscalEntity.getNumero());
		
		if(notas.isEmpty()) {
			
			notaFiscalEntity.setDataLancamento(LocalDate.now());
			notaFiscalService.salvar(notaFiscalEntity);
			temp.setIdNotaFiscalTemp(notaFiscalEntity.getId());

			return "redirect:/estoque/nota-fiscal/lancar/lancar-produto";
		}
		else {
			attr.addFlashAttribute("error", "Já existe uma nota fiscal cadastrada com esse número: "+notaFiscalEntity.getNumero());
			return "redirect:/estoque/nota-fiscal/lancar/nota-fiscal/"+temp.getIdFornecedorTemp();
		}
	}

	@GetMapping(PRE_LANCAR_NOTA_FISCAL)
	public String preLancarProduto(FornecedorEntity fornecedorEntity, NotaFiscalEntity notaFiscalEntity,
			ProdutoEntity produtoEntity, NotaFiscalAux notaFiscalAux, ModelMap modelMap) {

		modelMap.addAttribute("username", SecurityContextHolder.getContext()
		        .getAuthentication().getName());
		
		modelMap.addAttribute("fornecedorEntity", fornecedorService.buscarPorId(temp.getIdFornecedorTemp()));
		modelMap.addAttribute("notaFiscalEntity", notaFiscalService.buscarPorId(temp.getIdNotaFiscalTemp()));
		modelMap.addAttribute("produtos", produtoService.listarProdutosAtivos());

		return "estoque/nota-fiscal/lancar/lancar-produto";
	}

	@PostMapping(LANCAR_PRODUTO)
	public String lancarProduto(@Valid NotaFiscalAux notaFiscalAux, RedirectAttributes attr, ModelMap model) {

		model.addAttribute("username", SecurityContextHolder.getContext()
		        .getAuthentication().getName());
		
		Long idProduto = Long.parseLong(notaFiscalAux.getNomeProduto());
		NotaFiscalEntity notaFiscal = notaFiscalService.buscarPorId(temp.getIdNotaFiscalTemp());
		FornecedorEntity fornecedor = fornecedorService.buscarPorId(temp.getIdFornecedorTemp());
		ProdutoEntity produto = produtoService.buscarPorId(idProduto);
		NotaFiscalFornecedorProdutoEntity NFPEntity = new NotaFiscalFornecedorProdutoEntity(notaFiscal, fornecedor,
				produto, notaFiscalAux.getValorUnitario(), notaFiscalAux.getQtde());

		entradaDeProdutoComponent.lancamentoDeEntradaDeProduto(notaFiscalAux.getValorUnitario(),
				notaFiscalAux.getQtde(), idProduto);
		NFPService.salvar(NFPEntity);
		
		attr.addFlashAttribute("success", "Produto lançado com sucesso.");
		return "redirect:/estoque/nota-fiscal/lancar/lancar-produto";
	}

	// Acesso admin
	@GetMapping(NOTAS_FISCAIS_LANCADAS)
	public String notasFiscaisLancadas(NotaFiscalFornecedorProdutoEntity NFPEntity, ModelMap modelMap) {

		modelMap.addAttribute("username", SecurityContextHolder.getContext()
		        .getAuthentication().getName());
		
		modelMap.addAttribute("notasFiscais", notaFiscalService.listar());

		return "estoque/nota-fiscal/lista/notas-fiscais";
	}

	@GetMapping(PRE_EDITAR_NOTA_FISCAL_SELECIONADA)
	public String preEditarNotaFiscalSelecionarProduto(@PathVariable("id") Long id, ModelMap modelMap) {

		modelMap.addAttribute("username", SecurityContextHolder.getContext()
		        .getAuthentication().getName());
//		NotaFiscalEntity notaFiscalEntity = notaFiscalService.buscarPorId(id);
//		NotaFiscalFornecedorProdutoEntity NFP = NFPService.buscarNFPComIdNotaFiscal(notaFiscalEntity);

		return "estoque/nota-fiscal/lista/editar/selecionar-produto";
	}

}

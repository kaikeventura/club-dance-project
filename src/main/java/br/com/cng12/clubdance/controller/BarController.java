package br.com.cng12.clubdance.controller;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.cng12.clubdance.entity.ClienteEntity;
import br.com.cng12.clubdance.entity.ComandaEntity;
import br.com.cng12.clubdance.entity.EventoEntity;
import br.com.cng12.clubdance.entity.ProdutoEntity;
import br.com.cng12.clubdance.exceptions.EstoqueException;
import br.com.cng12.clubdance.service.impl.ClienteServiceImpl;
import br.com.cng12.clubdance.service.impl.ComandaProdutoServiceImpl;
import br.com.cng12.clubdance.service.impl.ComandaServiceImpl;
import br.com.cng12.clubdance.service.impl.EventoServiceImpl;
import br.com.cng12.clubdance.service.impl.ProdutoServiceImpl;
import br.com.cng12.clubdance.utils.components.ComandaProdutoComponent;
import br.com.cng12.clubdance.utils.components.TemporarioComponent;
import br.com.cng12.clubdance.utils.dto.ComandaAux;

@Controller
public class BarController {
	
	private static final String INICIO_BAR = "/bar/inicio";
	private static final String SELECIONAR_EVENTO_LISTA = "/bar/vender/selecionar-evento";
	private static final String SELECIONAR_EVENTO = "/bar/vender/selecionar-evento/{id}";
	private static final String SELECIONAR_CLIENTE = "/bar/vender/selecionar-cliente/{id}";
	private static final String SELECIONAR_PRODUTO = "/bar/vender/selecionar-produto";
	private static final String QUANTIDADE_ATUAL_EM_ESTOQUE = "/bar/vender/quantidade-produto";
	private static final String PRECO_DO_PRODUTO = "/bar/vender/preco-produto";
	private static final String SELECIONAR_VENDA_CLIENTE = "/bar/vender/editar/selecionar-cliente";

	@Autowired
	private EventoServiceImpl eventoService;

	@Autowired
	private TemporarioComponent temp;

	@Autowired
	private ClienteServiceImpl clienteService;

	@Autowired
	private ProdutoServiceImpl produtoService;

	@Autowired
	private ComandaProdutoServiceImpl comandaProdutoService;

	@Autowired
	private ComandaProdutoComponent comandaProdutoComponent;
	
	@Autowired
	private ComandaServiceImpl comandaServiceImpl;

	@GetMapping(INICIO_BAR)
	public String inicioBar() {

		return "bar/inicio-bar";
	}

	@GetMapping(SELECIONAR_EVENTO_LISTA)
	public String selecionarEventoLista(EventoEntity eventoEntity, ModelMap modelMap) {

		modelMap.addAttribute("eventos", eventoService.listarEventosAtivos());

		return "bar/vender/selecionar-evento";
	}

	@GetMapping(SELECIONAR_EVENTO)
	public String selecionarEvento(@PathVariable("id") Long id, ModelMap model, EventoEntity eventoEntity) {

		EventoEntity eventoEntity2 = eventoService.buscarPorId(id);
		
		ArrayList<ComandaEntity> comandasAbertas = comandaServiceImpl.listarComandasAbertas(eventoEntity2);
		
		ArrayList<ClienteEntity> clientesComAComandaAberta = new ArrayList<ClienteEntity>();
		
		for(int i = 0; i < comandasAbertas.size(); i++) {
			clientesComAComandaAberta.add(comandasAbertas.get(i).getClienteEntity());
		}
		
		model.addAttribute("eventoEntity", eventoService.buscarPorId(id));
		
		model.addAttribute("clientes", clientesComAComandaAberta);

		temp.setIdEventoTemp(id);

		return "bar/vender/selecionar-cliente";
	}

	@GetMapping(SELECIONAR_CLIENTE)
	public String selecionarCliente(@PathVariable("id") Long id, ModelMap model, EventoEntity eventoEntity,
			ClienteEntity clienteEntity, ProdutoEntity produtoEntity, ComandaAux comandaAux) {

		EventoEntity eventoEntity2 = eventoService.buscarPorId(temp.getIdEventoTemp());

		model.addAttribute("eventoEntity", eventoService.buscarPorId(eventoEntity2.getId()));
		model.addAttribute("clienteEntity", clienteService.buscarPorId(id));
		model.addAttribute("produtos", produtoService.listarProdutosAtivos());

		temp.setIdClienteTemp(id);

		return "bar/vender/selecionar-produto";
	}

	@PostMapping(SELECIONAR_PRODUTO)
	public String selecionarProduto(@Valid ComandaAux comandaAux, RedirectAttributes attr) throws EstoqueException {

		Long idProduto = Long.parseLong(comandaAux.getNomeProduto());
		ProdutoEntity produtoEntity = produtoService.buscarPorId(idProduto);

		if (produtoEntity.getQtdeEstoque() > 0) {
			if(comandaAux.getQtde() <= produtoEntity.getQtdeEstoque()) {
				comandaProdutoService.salvar(comandaProdutoComponent.novaComandaProdutoEntity(temp.getIdClienteTemp(),
						temp.getIdEventoTemp(), produtoEntity, comandaAux.getQtde()));
				int qtdeEstoque = produtoEntity.getQtdeEstoque() - comandaAux.getQtde();
				produtoService.retirarQtdeEstoque(qtdeEstoque, produtoEntity.getId());
				attr.addFlashAttribute("success", "O produto "+produtoEntity.getNome()+" foi vendido com sucesso.");
			}
			else {
				attr.addFlashAttribute("error", "A quantidade solicitada é maior que a quantidade disponível.");
				return "redirect:/bar/vender/selecionar-cliente/" + temp.getIdClienteTemp();
			}
		}
		else {
			attr.addFlashAttribute("error", "O estoque do produto "+produtoEntity.getNome()+" está zerado.");
			return "redirect:/bar/vender/selecionar-cliente/" + temp.getIdClienteTemp();
		}

		return "redirect:/bar/vender/selecionar-cliente/" + temp.getIdClienteTemp();
	}

	@GetMapping(QUANTIDADE_ATUAL_EM_ESTOQUE)
	public @ResponseBody int quantidadeAtualProduto(@RequestParam Long id) {

		ProdutoEntity produtoEntity = produtoService.buscarPorId(id);
		
		return produtoEntity.getQtdeEstoque();
	}
	
	@GetMapping(PRECO_DO_PRODUTO)
	public @ResponseBody Double precoProduto(@RequestParam Long id) {

		ProdutoEntity produtoEntity = produtoService.buscarPorId(id);
		
		return produtoEntity.getPreco();
	}
	
	@GetMapping(SELECIONAR_VENDA_CLIENTE)
	public String slecionarVendaCliente(ComandaAux comandaVendaProdutoDTO, ModelMap model) {
		
//		model.addAttribute("comandaVendaProdutoDTO", comandaService.buscarClientesDoEventoComAComanda());
		
		return "bar/vender/editar/selecionar-cliente";
	}
}

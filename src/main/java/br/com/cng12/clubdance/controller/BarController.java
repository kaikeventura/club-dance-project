package br.com.cng12.clubdance.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.cng12.clubdance.entity.ClienteEntity;
import br.com.cng12.clubdance.entity.EventoEntity;
import br.com.cng12.clubdance.entity.ProdutoEntity;
import br.com.cng12.clubdance.service.impl.ClienteServiceImpl;
import br.com.cng12.clubdance.service.impl.ComandaProdutoServiceImpl;
import br.com.cng12.clubdance.service.impl.EventoServiceImpl;
import br.com.cng12.clubdance.service.impl.ProdutoServiceImpl;
import br.com.cng12.clubdance.utils.auxiliares.ComandaAux;
import br.com.cng12.clubdance.utils.components.ComandaProdutoComponent;
import br.com.cng12.clubdance.utils.components.TemporarioComponent;

@Controller
public class BarController {

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

	@GetMapping("/bar/inicio")
	public String inicioBar() {

		return "bar/inicio-bar";
	}

	@GetMapping("/bar/vender/selecionar-evento")
	public String selecionarEvento(EventoEntity eventoEntity, ModelMap modelMap) {

		modelMap.addAttribute("eventos", eventoService.listarEventosAtivos());

		return "bar/vender/selecionar-evento";
	}

	@GetMapping("/bar/vender/selecionar-evento/{id}")
	public String selecionarEvento(@PathVariable("id") Long id, ModelMap model, EventoEntity eventoEntity) {

		EventoEntity eventoEntity2 = eventoService.buscarPorId(id);

		model.addAttribute("eventoEntity", eventoService.buscarPorId(id));
		model.addAttribute("clientes", clienteService.buscarClientesDoEvento(eventoEntity2));

		temp.setIdEventoTemp(id);

		return "bar/vender/selecionar-cliente";
	}

	@GetMapping("/bar/vender/selecionar-cliente/{id}")
	public String selecionarCliente(@PathVariable("id") Long id, ModelMap model, EventoEntity eventoEntity,
			ClienteEntity clienteEntity, ProdutoEntity produtoEntity, ComandaAux comandaAux) {

		EventoEntity eventoEntity2 = eventoService.buscarPorId(temp.getIdEventoTemp());

		model.addAttribute("eventoEntity", eventoService.buscarPorId(eventoEntity2.getId()));
		model.addAttribute("clienteEntity", clienteService.buscarPorId(id));
		model.addAttribute("produtos", produtoService.listarProdutosAtivos());

		temp.setIdClienteTemp(id);

		return "bar/vender/selecionar-produto";
	}

	@PostMapping("/bar/vender/selecionar-produto")
	public String selecionarProduto(@Valid ComandaAux comandaAux) {

		Long idProduto = Long.parseLong(comandaAux.getNomeProduto());
		ProdutoEntity produtoEntity = produtoService.buscarPorId(idProduto);

		comandaProdutoService.salvar(comandaProdutoComponent.novaComandaProdutoEntity(temp.getIdClienteTemp(),
				temp.getIdEventoTemp(), produtoEntity, comandaAux.getQtde()));

		return "redirect:/bar/vender/selecionar-cliente/" + temp.getIdClienteTemp();
	}
}

package br.com.cng12.clubdance.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.cng12.clubdance.entity.ClienteEntity;
import br.com.cng12.clubdance.entity.ComandaEntity;
import br.com.cng12.clubdance.entity.EventoEntity;
import br.com.cng12.clubdance.exceptions.IngressoException;
import br.com.cng12.clubdance.service.impl.ClienteServiceImpl;
import br.com.cng12.clubdance.service.impl.ComandaServiceImpl;
import br.com.cng12.clubdance.service.impl.EventoServiceImpl;
import br.com.cng12.clubdance.utils.ControleDeCapacidadeEvento;

@Controller
public class ClienteController {

	@Autowired
	private ComandaServiceImpl comandaService;

	@Autowired
	private ClienteServiceImpl clienteService;

	@Autowired
	private EventoController eventoController;
	
	@Autowired
	private ControleDeCapacidadeEvento controleDeCapacidadeEvento;
	
	@Autowired
	private EventoServiceImpl eventoService;

	protected Long idCliente;

	/*
	 * Quando o cliente realiza a compra do ingresso é criado um objeto do mesmo, e
	 * respectivamente cria uma comanda, o metodo abaixo cria a comanda.
	 */
	public void criarComanda(ClienteEntity clienteEntity, EventoEntity eventoEntity, Double precoIngresso) {

		ComandaEntity comandaEntity = new ComandaEntity();
		comandaService.salvar(comandaEntity, clienteEntity, eventoEntity, precoIngresso);
	}

	@GetMapping("/evento/venda/editar-venda/{id}")
	public String preEditarEvento(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("clienteEntity", clienteService.buscarPorId(id));
		this.idCliente = id;

		return "evento/venda/editar-venda-ingresso";
	}

	@PostMapping("/evento/venda/editar-venda")
	public String editarEvento(@Valid ClienteEntity clienteEntity) throws IngressoException {
		
		ClienteEntity clienteEntityAtual = clienteService.buscarPorId(clienteEntity.getId());
		EventoEntity eventoEntity = eventoService.buscarPorId(eventoController.getIdEvento());

		if (clienteEntity.getTipoIngresso().equals("CAMAROTE")) {
			if(controleDeCapacidadeEvento.vendaIngressoCamarote(eventoEntity) == true) {
				clienteService.editar(clienteEntity.getCpf(), clienteEntity.getNome(), clienteEntity.getTipoIngresso(),
						clienteEntity.getId());
				controleDeCapacidadeEvento.retornaIngressoNormalVip(eventoEntity);
			}
			else{
				throw new IngressoException("CAMAROTES ESGOTADOS");
			}
		}
		else {
			if(clienteEntityAtual.getTipoIngresso().equals("NORMAL") && clienteEntity.getTipoIngresso().equals("VIP")) {
				clienteService.editar(clienteEntity.getCpf(), clienteEntity.getNome(), clienteEntity.getTipoIngresso(),
						clienteEntity.getId());
			}
			else if(clienteEntityAtual.getTipoIngresso().equals("VIP") && clienteEntity.getTipoIngresso().equals("NORMAL")) {
				clienteService.editar(clienteEntity.getCpf(), clienteEntity.getNome(), clienteEntity.getTipoIngresso(),
						clienteEntity.getId());
			}
			else if(controleDeCapacidadeEvento.vendaIngressoNormalEVip(eventoEntity) == true) {
				clienteService.editar(clienteEntity.getCpf(), clienteEntity.getNome(), clienteEntity.getTipoIngresso(),
						clienteEntity.getId());
				controleDeCapacidadeEvento.retornaIngressoCamarote(eventoEntity);
			}
			else{
				throw new IngressoException("INGRESSOS ESGOTADOS");
			}
		}

		return "redirect:/evento/eventos";
	}

	// Erro ao chamar a página
	@GetMapping("/evento/venda/buscar/cpf")
	public String buscarClientePorCpf(@RequestParam("cpf") String cpf, ModelMap model) {
		model.addAttribute("clientes", clienteService.buscarPorCpf(cpf, eventoController.getIdEvento()));

		return "evento/venda/venda-ingresso";
	}

}

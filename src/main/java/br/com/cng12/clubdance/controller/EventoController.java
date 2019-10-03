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
import br.com.cng12.clubdance.entity.EventoEntity;
import br.com.cng12.clubdance.exceptions.IngressoException;
import br.com.cng12.clubdance.service.impl.ClienteServiceImpl;
import br.com.cng12.clubdance.service.impl.EventoServiceImpl;
import br.com.cng12.clubdance.utils.ControleDeCapacidadeEvento;
import lombok.Getter;

@Controller
public class EventoController {

	// Injeção de dependência
	@Autowired
	private EventoServiceImpl eventoService;

	// Injeção de dependência
	@Autowired
	private ClienteServiceImpl clienteService;

	// Injeção de dependência
	@Autowired
	private ClienteController clienteController;

	// Injeção de dependência
	@Autowired
	private ControleDeCapacidadeEvento controleDeCapacidadeEvento;

	@Getter
	private Long idEvento = 0L;

	// Chama a página para renderizar
	@GetMapping("/evento/cadastrar-evento")
	public String cadastrarEvento(EventoEntity eventoEntity) {
		return "evento/cadastrar-evento";
	}

	@PostMapping("/evento/cadastrar-evento")
	public String salvarEvento(@Valid EventoEntity eventoEntity) {

		eventoService.salvar(eventoEntity);

		return "redirect:/evento/cadastrar-evento";
	}

	@GetMapping("/evento/eventos")
	public String listarEventos(ModelMap model) {

		model.addAttribute("eventos", eventoService.listar());

		return "evento/eventos";
	}

	@GetMapping("/evento/editar-evento/{id}")
	public String preEditarEvento(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("eventoEntity", eventoService.buscarPorId(id));
		return "evento/cadastrar-evento";
	}

	@PostMapping("/evento/editar-evento")
	public String editarEvento(@Valid EventoEntity eventoEntity) {

		eventoService.editar(eventoEntity);

		return "redirect:/evento/cadastrar-evento";
	}

	@GetMapping("/evento/excluir-evento/{id}")
	public String excluirEvento(@PathVariable("id") Long id) {

		eventoService.excluir(id);

		return "redirect:/evento/eventos";
	}

	@GetMapping("/evento/vender-ingresso/{id}")
	public String preVendaIngresso(@PathVariable("id") Long id, ModelMap model, ClienteEntity clienteEntity) {

		this.idEvento = id;
		model.addAttribute("eventoEntity", eventoService.buscarPorId(id));
		model.addAttribute("clientes", clienteService.listaClientesDoEvento(this.idEvento));
		return "evento/venda/venda-ingresso";
	}

	@PostMapping("/evento/vender-ingresso-cliente")
	public String venderIngressoCliente(@Valid ClienteEntity clienteEntity) throws IngressoException {

		EventoEntity eventoEntity = eventoService.buscarPorId(idEvento);
		clienteEntity.setEventoEntity(eventoEntity);
		Double valorIngresso = 0.0D;

		if (controleDeCapacidadeEvento.verificaSeAQuantidadeDoEventoZerou(eventoEntity) == false) {

			if (clienteEntity.getTipoIngresso().equals("NORMAL")) {
				valorIngresso = eventoEntity.getPrecoIngressoNormal();
				controleDeCapacidadeEvento.vendaIngressoNormalEVip(eventoEntity);
				clienteService.salvar(clienteEntity);
				clienteController.criarComanda(clienteEntity, eventoEntity, valorIngresso);
			} 
			else if (clienteEntity.getTipoIngresso().equals("VIP")) {
				valorIngresso = eventoEntity.getPrecoIngressoVip();
				controleDeCapacidadeEvento.vendaIngressoNormalEVip(eventoEntity);
				clienteService.salvar(clienteEntity);
				clienteController.criarComanda(clienteEntity, eventoEntity, valorIngresso);
			}
			else if (clienteEntity.getTipoIngresso().equals("CAMAROTE")) {
				valorIngresso = eventoEntity.getPrecoIngressoCamarote();
				controleDeCapacidadeEvento.vendaIngressoCamarote(eventoEntity);
				clienteService.salvar(clienteEntity);
				clienteController.criarComanda(clienteEntity, eventoEntity, valorIngresso);
			}
		} 
//		else if (controleDeCapacidadeEvento.verificaSeAQuantidadeDoEventoZerou(eventoEntity) == true
//				&& clienteEntity.getTipoIngresso().equals("CAMAROTE")) {
//			if(controleDeCapacidadeEvento.verificaSeACapacidadeDoCamaroteExcedeu(eventoEntity) == false) {
//				valorIngresso = eventoEntity.getPrecoIngressoCamarote();
//				controleDeCapacidadeEvento.vendaIngressoCamarote(eventoEntity);
//				clienteService.salvar(clienteEntity);
//				clienteController.criarComanda(clienteEntity, eventoEntity, valorIngresso);
//			} 
//			
//		}
		else {
			throw new IngressoException("O estoque de ingressos acabaram!");
		}

		return "redirect:/evento/vender-ingresso/" + idEvento;
	}

	@GetMapping("/evento/buscar/nome")
	public String buscarEventoPorNome(@RequestParam("nome") String nome, ModelMap model) {
		model.addAttribute("eventos", eventoService.buscarPorNome(nome));
		return "evento/eventos";
	}

}

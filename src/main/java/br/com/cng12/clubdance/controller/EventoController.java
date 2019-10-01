package br.com.cng12.clubdance.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.cng12.clubdance.entity.ClienteEntity;
import br.com.cng12.clubdance.entity.EventoEntity;
import br.com.cng12.clubdance.service.impl.ClienteServiceImpl;
import br.com.cng12.clubdance.service.impl.EventoServiceImpl;

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
	private	ClienteController clienteController;
	
	protected Long idEvento = 0L;

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
	public String editarEvento(@Valid EventoEntity eventoEntity, BindingResult bindingResult) {

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
	public String venderIngressoCliente(@Valid ClienteEntity clienteEntity) {

		EventoEntity eventoEntity = eventoService.buscarPorId(idEvento);
		clienteEntity.setEventoEntity(eventoEntity);
		clienteService.salvar(clienteEntity);
		
		Double valorIngresso = 0.0D;
		
		if(clienteEntity.getTipoIngresso().equals("NORMAL")) {
			valorIngresso = eventoEntity.getPrecoIngressoNormal();
		} 
		else if(clienteEntity.getTipoIngresso().equals("VIP")) {
			valorIngresso = eventoEntity.getPrecoIngressoVip();
		}
		else if(clienteEntity.getTipoIngresso().equals("CAMAROTE")) {
			valorIngresso = eventoEntity.getPrecoIngressoCamarote();
		}
		
		clienteController.criarComanda(clienteEntity, eventoEntity, valorIngresso);
		
		return "redirect:/evento/vender-ingresso/" + idEvento;
	}

}

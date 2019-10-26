package br.com.cng12.clubdance.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.cng12.clubdance.entity.ClienteEntity;
import br.com.cng12.clubdance.entity.EventoEntity;
import br.com.cng12.clubdance.exceptions.IngressoException;
import br.com.cng12.clubdance.service.impl.ClienteServiceImpl;
import br.com.cng12.clubdance.service.impl.EventoServiceImpl;
import br.com.cng12.clubdance.utils.Status;
import br.com.cng12.clubdance.utils.components.ControleDeCapacidadeEventoComponent;
import lombok.Getter;

@Controller
public class EventoController {

	@Autowired
	private EventoServiceImpl eventoService;

	@Autowired
	private ClienteServiceImpl clienteService;

	@Autowired
	private ClienteController clienteController;

	@Autowired
	private ControleDeCapacidadeEventoComponent controleDeCapacidadeEvento;

	@Getter
	private Long idEvento = 0L;

	@GetMapping("/evento/cadastrar-evento")
	public String cadastroDeEvento(EventoEntity eventoEntity) {
		return "evento/cadastrar-evento";
	}

	@PostMapping("/evento/cadastrar-evento")
	public String salvarEvento(@Valid EventoEntity eventoEntity, BindingResult bindingResult, RedirectAttributes attr) {

		if (bindingResult.hasErrors()) {
			return "evento/cadastrar-evento";
		}

		eventoService.salvar(eventoEntity);
		attr.addFlashAttribute("success", "Salvo com sucesso.");

		return "redirect:/evento/cadastrar-evento";
	}

	@ModelAttribute("status")
	public Status[] getStatus() {
		return Status.values();
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
	public String editarEvento(@Valid EventoEntity eventoEntity, RedirectAttributes attr) {

		eventoService.editar(eventoEntity);
		attr.addFlashAttribute("success", "Salvo com sucesso.");

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
	public String venderIngressoCliente(@Valid ClienteEntity clienteEntity, RedirectAttributes attr)
			throws IngressoException {

		EventoEntity eventoEntity = eventoService.buscarPorId(idEvento);
		clienteEntity.setEventoEntity(eventoEntity);
		Double valorIngresso = 0.0D;

		if (clienteEntity.getTipoIngresso().equals("NORMAL")) {
			valorIngresso = eventoEntity.getPrecoIngressoNormal();
			if (controleDeCapacidadeEvento.vendaIngressoNormalEVip(eventoEntity) == true) {
				clienteService.salvar(clienteEntity);
				clienteController.criarComanda(clienteEntity, eventoEntity, valorIngresso);
				attr.addFlashAttribute("success", "Ingresso vendido com sucesso.");
			} else {
				attr.addFlashAttribute("error", "Ingressos do tipo NORMAL esgotaram.");
			}
			
		}
		if (clienteEntity.getTipoIngresso().equals("VIP")) {
			valorIngresso = eventoEntity.getPrecoIngressoVip();
			if (controleDeCapacidadeEvento.vendaIngressoNormalEVip(eventoEntity) == true) {
				clienteService.salvar(clienteEntity);
				clienteController.criarComanda(clienteEntity, eventoEntity, valorIngresso);
				attr.addFlashAttribute("success", "Ingresso vendido com sucesso.");
			} else {
				attr.addFlashAttribute("error", "Ingressos do tipo VIP esgotaram.");
			}

		}
		if (clienteEntity.getTipoIngresso().equals("CAMAROTE")) {
			valorIngresso = eventoEntity.getPrecoIngressoCamarote();
			if (controleDeCapacidadeEvento.vendaIngressoCamarote(eventoEntity) == true) {
				clienteService.salvar(clienteEntity);
				clienteController.criarComanda(clienteEntity, eventoEntity, valorIngresso);
				attr.addFlashAttribute("success", "Ingresso vendido com sucesso.");
			} else {
				attr.addFlashAttribute("error", "Ingressos do tipo CAMAROTE esgotaram.");
			}
			
		}

		return "redirect:/evento/vender-ingresso/" + idEvento;
	}

	@GetMapping("/evento/buscar/nome")
	public String buscarEventoPorNome(@RequestParam("nome") String nome, ModelMap model) {
		model.addAttribute("eventos", eventoService.buscarPorNome(nome));
		return "evento/eventos";
	}

}

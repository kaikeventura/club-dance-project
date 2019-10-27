package br.com.cng12.clubdance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.cng12.clubdance.dao.ComandaDAO;
import br.com.cng12.clubdance.dao.ComandaProdutoDAO;
import br.com.cng12.clubdance.entity.ClienteEntity;
import br.com.cng12.clubdance.entity.ComandaEntity;
import br.com.cng12.clubdance.entity.ComandaProdutoEntity;
import br.com.cng12.clubdance.entity.EventoEntity;
import br.com.cng12.clubdance.service.impl.ClienteServiceImpl;
import br.com.cng12.clubdance.service.impl.ComandaProdutoServiceImpl;
import br.com.cng12.clubdance.service.impl.ComandaServiceImpl;
import br.com.cng12.clubdance.service.impl.EventoServiceImpl;
import br.com.cng12.clubdance.utils.components.TemporarioComponent;
import br.com.cng12.clubdance.utils.dto.TotalDTO;

@Controller
public class CaixaController {

	@Autowired
	private EventoServiceImpl eventoService;

	@Autowired
	private TemporarioComponent temp;

	@Autowired
	private ClienteServiceImpl clienteService;

	@Autowired
	private ComandaServiceImpl comandaService;

	@Autowired
	private ComandaProdutoServiceImpl comandaProdutoService;

	@GetMapping("/caixa/inicio-caixa")
	public String paginaInicialDoCaixa() {

		return "caixa/inicio-caixa";
	}

	@GetMapping("/caixa/cobranca/selecionar-evento")
	public String selecionarEvento(EventoEntity eventoEntity, ModelMap modelMap) {

		modelMap.addAttribute("eventos", eventoService.listarEventosAtivos());

		return "caixa/cobranca/selecionar-evento";
	}

	@GetMapping("/caixa/cobranca/selecionar-evento/{id}")
	public String selecionarEvento(@PathVariable("id") Long id, ModelMap model, EventoEntity eventoEntity) {

		EventoEntity eventoEntity2 = eventoService.buscarPorId(id);

		model.addAttribute("eventoEntity", eventoService.buscarPorId(id));
		model.addAttribute("clientes", clienteService.buscarClientesDoEvento(eventoEntity2));

		temp.setIdEventoTempCaixa(id);

		return "caixa/cobranca/cliente/selecionar-cliente";
	}

	@GetMapping("/caixa/cobranca/cliente/selecionar-cliente/{id}")
	public String selecionarCliente(@PathVariable("id") Long id, ModelMap model, EventoEntity eventoEntity,
			ClienteEntity clienteEntity, ComandaEntity comandaEntity, ComandaProdutoEntity comandaProdutoEntity,
			TotalDTO dto) {

		ClienteEntity cliente = clienteService.buscarPorId(id);
		EventoEntity evento = eventoService.buscarPorId(temp.getIdEventoTempCaixa());
		ComandaEntity comanda = comandaService.buscarComandaDoCliente(clienteEntity);
		List<ComandaProdutoEntity> comandaProdutos = comandaProdutoService.buscarLancamentosDaComanda(comanda);
		TotalDTO totalDTO = new TotalDTO();
		Double totalComanda = 0D;
		
		for (int i = 0; i < comandaProdutos.size(); i++) {
			totalComanda += comandaProdutos.get(i).getValorTotal();
		}
		
		totalDTO.setTotalComanda(totalComanda);
		totalDTO.setTotal(totalComanda + comanda.getPrecoIngresso());

		model.addAttribute("eventoEntity", eventoService.buscarPorId(evento.getId()));
		model.addAttribute("clienteEntity", clienteService.buscarPorId(id));
		model.addAttribute("comandaProdutos", comandaProdutoService.buscarLancamentosDaComanda(comanda));
		model.addAttribute("comanda", comandaService.buscarComandaDoCliente(clienteEntity));
		model.addAttribute("cliente", clienteService.buscarPorId(id));
		model.addAttribute("valorTotalAPagar", totalDTO);

		System.out.println(comandaProdutos.toString());

		temp.setIdClienteTempCaixa(id);

		return "caixa/cobranca/cliente/comanda";
	}

	@GetMapping("/caixa/cobranca/cliente/")
	public String comandaCliente() {

		return "";
	}

}

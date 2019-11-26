package br.com.cng12.clubdance.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.cng12.clubdance.entity.CartaoCreditoEntity;
import br.com.cng12.clubdance.entity.CartaoDebitoEntity;
import br.com.cng12.clubdance.entity.ClienteEntity;
import br.com.cng12.clubdance.entity.ComandaEntity;
import br.com.cng12.clubdance.entity.ComandaProdutoEntity;
import br.com.cng12.clubdance.entity.EventoEntity;
import br.com.cng12.clubdance.entity.PagamentoCaixaEntity;
import br.com.cng12.clubdance.service.impl.CartaoCreditoServiceImpl;
import br.com.cng12.clubdance.service.impl.CartaoDebitoServiceImpl;
import br.com.cng12.clubdance.service.impl.ClienteServiceImpl;
import br.com.cng12.clubdance.service.impl.ComandaProdutoServiceImpl;
import br.com.cng12.clubdance.service.impl.ComandaServiceImpl;
import br.com.cng12.clubdance.service.impl.EventoServiceImpl;
import br.com.cng12.clubdance.service.impl.PagamentoCaixaServiceImpl;
import br.com.cng12.clubdance.utils.FormaPagamento;
import br.com.cng12.clubdance.utils.components.TemporarioComponent;
import br.com.cng12.clubdance.utils.dto.TotalDTO;

@Controller
public class CaixaController {
	
	private static final String PAGINA_INICIAL_CAIXA = "/caixa/inicio-caixa";
	private static final String SELECIONAR_EVENTO_LISTA = "/caixa/cobranca/selecionar-evento";
	private static final String SELECIONAR_EVENTO = "/caixa/cobranca/selecionar-evento/{id}";
	private static final String SELECIONAR_CLIENTE = "/caixa/cobranca/cliente/selecionar-cliente/{id}";
	private static final String CIELO_MAQUININHA = "/cielo/cielo-pagamentos";
	private static final String COMANDA_CLIENTE = "/cielo/cielo-pagamentos/processa-pagamento";
	private static final String PAGAMENTO_DINHEIRO = "/caixa/cobranca/pagamentos/dinheiro";
	private static final String PAGAMENTO_APROVADO = "/cielo/cielo-pagamentos/aprovado";

	@Autowired
	private EventoServiceImpl eventoService;

	@Autowired
	private TemporarioComponent temp;

	@Autowired
	private ClienteServiceImpl clienteService;

	@Autowired
	private ComandaServiceImpl comandaService;

	@Autowired
	private PagamentoCaixaServiceImpl pagamentoService;

	@Autowired
	private ComandaProdutoServiceImpl comandaProdutoService;

	@Autowired
	private CartaoCreditoServiceImpl cartaoCreditoService;

	@Autowired
	private CartaoDebitoServiceImpl cartaoDebitoService;

	@Autowired
	private ComandaServiceImpl comandaServiceImpl;
	
	TotalDTO totalDTO = new TotalDTO();

	@GetMapping(PAGINA_INICIAL_CAIXA)
	public String paginaInicialDoCaixa(ModelMap model) {

		model.addAttribute("username", SecurityContextHolder.getContext()
		        .getAuthentication().getName());
		
		return "caixa/inicio-caixa";
	}

	@GetMapping(SELECIONAR_EVENTO_LISTA)
	public String selecionarEventoLista(EventoEntity eventoEntity, ModelMap modelMap) {

		modelMap.addAttribute("username", SecurityContextHolder.getContext()
		        .getAuthentication().getName());
		modelMap.addAttribute("eventos", eventoService.listarEventosAtivos());

		return "caixa/cobranca/selecionar-evento";
	}

	@GetMapping(SELECIONAR_EVENTO)
	public String selecionarEvento(@PathVariable("id") Long id, ModelMap model, EventoEntity eventoEntity) {

		model.addAttribute("username", SecurityContextHolder.getContext()
		        .getAuthentication().getName());
		
		EventoEntity eventoEntity2 = eventoService.buscarPorId(id);
		
		ArrayList<ComandaEntity> comandasAbertas = comandaServiceImpl.listarComandasAbertas(eventoEntity2);
		
		ArrayList<ClienteEntity> clientesComAComandaAberta = new ArrayList<ClienteEntity>();
		
		for(int i = 0; i < comandasAbertas.size(); i++) {
			clientesComAComandaAberta.add(comandasAbertas.get(i).getClienteEntity());
		}
		
		model.addAttribute("eventoEntity", eventoService.buscarPorId(id));
		
		model.addAttribute("clientes", clientesComAComandaAberta);

		temp.setIdEventoTempCaixa(id);

		return "caixa/cobranca/cliente/selecionar-cliente";
	}

	@GetMapping(SELECIONAR_CLIENTE)
	public String selecionarCliente(@PathVariable("id") Long id, ModelMap model, EventoEntity eventoEntity,
			ClienteEntity clienteEntity, ComandaEntity comandaEntity, ComandaProdutoEntity comandaProdutoEntity,
			TotalDTO dto, PagamentoCaixaEntity pagamentoCaixaEntity) {

		model.addAttribute("username", SecurityContextHolder.getContext()
		        .getAuthentication().getName());
		
		EventoEntity evento = eventoService.buscarPorId(temp.getIdEventoTempCaixa());
		ComandaEntity comanda = comandaService.buscarComandaDoCliente(clienteEntity);
		List<ComandaProdutoEntity> comandaProdutos = comandaProdutoService.buscarLancamentosDaComanda(comanda);

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

		temp.setIdClienteTempCaixa(id);

		return "caixa/cobranca/cliente/pagamento/comanda";
	}

	@ModelAttribute("formasPagamento")
	public FormaPagamento[] getFormasPagamento() {
		return FormaPagamento.values();
	}

	@GetMapping(CIELO_MAQUININHA)
	public String cielo(ModelMap model, EventoEntity eventoEntity, ClienteEntity clienteEntity,
			ComandaEntity comandaEntity, ComandaProdutoEntity comandaProdutoEntity, TotalDTO dto,
			PagamentoCaixaEntity pagamentoCaixaEntity) {

		model.addAttribute("username", SecurityContextHolder.getContext()
		        .getAuthentication().getName());
		model.addAttribute("valorTotalAPagar", totalDTO);

		return "cielo/cielo";
	}

	@PostMapping(COMANDA_CLIENTE)
	public String comandaCliente(@Valid PagamentoCaixaEntity pagamentoCaixaEntity, RedirectAttributes attr) {
		
		EventoEntity evento = eventoService.buscarPorId(temp.getIdEventoTempCaixa());
		ClienteEntity cliente = clienteService.buscarPorId(temp.getIdClienteTempCaixa());
		ComandaEntity comanda = comandaService.buscarComandaDoCliente(cliente);

		pagamentoCaixaEntity.setData(LocalDate.now());
		pagamentoCaixaEntity.setClienteEntity(cliente);
		pagamentoCaixaEntity.setEventoEntity(evento);
		pagamentoCaixaEntity.setValor(totalDTO.getTotal());

		if (pagamentoCaixaEntity.getFormaPagamento().equals("CREDITO")) {
			List<CartaoCreditoEntity> cartaoCredito = cartaoCreditoService
					.buscarPorNumeroDoCartao(pagamentoCaixaEntity.getNumeroCartao());

			if (!cartaoCredito.isEmpty()) {

				if (cartaoCredito.get(0).getSenha() == pagamentoCaixaEntity.getSenha()) {

					pagamentoCaixaEntity.setSenha(0000);
					pagamentoCaixaEntity.setNumeroCartao(
							"****-****-****-" + pagamentoCaixaEntity.getNumeroCartao().substring(15, 19));

					pagamentoService.salvar(pagamentoCaixaEntity);
					comandaService.atualizaStatusComanda("FECHADO", comanda.getId());
					cartaoCreditoService.atualizaLimiteCartao(
							(cartaoCredito.get(0).getLimite() - pagamentoCaixaEntity.getValor()),
							cartaoCredito.get(0).getId());

					attr.addFlashAttribute("success", "Pagamento aprovado.");
					return "redirect:/cielo/cielo-pagamentos/aprovado";
				} else {
					attr.addFlashAttribute("error", "Senha incorreta.");
					return "redirect:/cielo/cielo-pagamentos";
				}

			} else {
				attr.addFlashAttribute("error", "Cartão inválido.");
				return "redirect:/cielo/cielo-pagamentos";
			}
		}
		if (pagamentoCaixaEntity.getFormaPagamento().equals("DEBITO")) {
			List<CartaoDebitoEntity> cartaoDebito = cartaoDebitoService
					.buscarPorNumeroDoCartao(pagamentoCaixaEntity.getNumeroCartao());

			if (!cartaoDebito.isEmpty()) {

				if (cartaoDebito.get(0).getSenha() == pagamentoCaixaEntity.getSenha()) {

					pagamentoCaixaEntity.setSenha(0000);
					pagamentoCaixaEntity.setNumeroCartao(
							"****-****-****-" + pagamentoCaixaEntity.getNumeroCartao().substring(15, 19));

					pagamentoService.salvar(pagamentoCaixaEntity);
					comandaService.atualizaStatusComanda("FECHADO", comanda.getId());
					cartaoCreditoService.atualizaLimiteCartao(
							(cartaoDebito.get(0).getSaldo() - pagamentoCaixaEntity.getValor()),
							cartaoDebito.get(0).getId());

					attr.addFlashAttribute("success", "Pagamento aprovado.");
					return "redirect:/cielo/cielo-pagamentos/aprovado";
				} else {
					attr.addFlashAttribute("error", "Senha incorreta.");
					return "redirect:/cielo/cielo-pagamentos";
				}

			} else {
				attr.addFlashAttribute("error", "Cartão inválido.");
				return "redirect:/cielo/cielo-pagamentos";
			}
		}
		else {
			
			pagamentoCaixaEntity.setSenha(0000);
			pagamentoCaixaEntity.setNumeroCartao("N/A");
			pagamentoService.salvar(pagamentoCaixaEntity);
			comandaService.atualizaStatusComanda("FECHADO", comanda.getId());
			
			return "redirect:/caixa/cobranca/selecionar-evento";
		}
		
	}
	
	@GetMapping(PAGAMENTO_DINHEIRO)
	public String pagamentoComDinheiro(RedirectAttributes attr, ModelMap model) {
		
		model.addAttribute("username", SecurityContextHolder.getContext()
		        .getAuthentication().getName());
		
		PagamentoCaixaEntity pagamentoCaixaEntity = new PagamentoCaixaEntity();
		
		EventoEntity evento = eventoService.buscarPorId(temp.getIdEventoTempCaixa());
		ClienteEntity cliente = clienteService.buscarPorId(temp.getIdClienteTempCaixa());
		ComandaEntity comanda = comandaService.buscarComandaDoCliente(cliente);

		pagamentoCaixaEntity.setData(LocalDate.now());
		pagamentoCaixaEntity.setClienteEntity(cliente);
		pagamentoCaixaEntity.setEventoEntity(evento);
		pagamentoCaixaEntity.setValor(totalDTO.getTotal());
		pagamentoCaixaEntity.setFormaPagamento("DINHEIRO");
		
		pagamentoCaixaEntity.setSenha(0000);
		pagamentoCaixaEntity.setNumeroCartao("N/A");
		pagamentoService.salvar(pagamentoCaixaEntity);
		comandaService.atualizaStatusComanda("FECHADO", comanda.getId());
		
		attr.addFlashAttribute("success", "Pagamento realizado com dinheiro.");
		
		return "redirect:/caixa/cobranca/selecionar-evento";
	}
	
	@GetMapping(PAGAMENTO_APROVADO)
	public String pagamentoAprovado() {
		
		return "cielo/pagamento-aprovado";
	}

}

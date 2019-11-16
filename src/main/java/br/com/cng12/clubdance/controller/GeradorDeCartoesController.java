package br.com.cng12.clubdance.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.cng12.clubdance.entity.CartaoCreditoEntity;
import br.com.cng12.clubdance.entity.CartaoDebitoEntity;
import br.com.cng12.clubdance.service.impl.CartaoCreditoServiceImpl;
import br.com.cng12.clubdance.service.impl.CartaoDebitoServiceImpl;

@Controller
public class GeradorDeCartoesController {
	
	private static final String GERADOR_CARTOES = "/cartoes";
	private static final String GERADOR_CARTAO_DE_CREDITO = "/cartoes/gerador-cartoes/gerar-cartao-credito";
	private static final String GERADOR_CARTAO_DE_DEBITO = "/cartoes/gerador-cartoes/gerar-cartao-debito";

	@Autowired
	private CartaoCreditoServiceImpl cartaoCreditoService;

	@Autowired
	private CartaoDebitoServiceImpl cartaoDebitoService;

	@GetMapping(GERADOR_CARTOES)
	public String geradorCartoes(CartaoCreditoEntity cartaoCreditoEntity, CartaoDebitoEntity cartaoDebitoEntity) {

		return "cartoes/gerador-cartoes";
	}

	@PostMapping(GERADOR_CARTAO_DE_CREDITO)
	public String gerarCartaoCredito(@Valid CartaoCreditoEntity cartaoCreditoEntity, RedirectAttributes attr) {

		cartaoCreditoService.salvar(cartaoCreditoEntity);
		attr.addFlashAttribute("successcredito", "Cartão de crédito gerado com sucesso.");

		return "redirect:/cartoes";
	}

	@PostMapping(GERADOR_CARTAO_DE_DEBITO)
	public String gerarCartaoDebito(@Valid CartaoDebitoEntity cartaoDebitoEntity, RedirectAttributes attr) {

		cartaoDebitoService.salvar(cartaoDebitoEntity);
		attr.addFlashAttribute("successdebito", "Cartão de débito gerado com sucesso.");

		return "redirect:/cartoes";
	}

}

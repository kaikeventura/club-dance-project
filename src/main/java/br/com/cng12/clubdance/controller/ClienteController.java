package br.com.cng12.clubdance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.cng12.clubdance.entity.ClienteEntity;
import br.com.cng12.clubdance.entity.ComandaEntity;
import br.com.cng12.clubdance.entity.EventoEntity;
import br.com.cng12.clubdance.service.ComandaService;

@Controller
public class ClienteController {

	@Autowired
	private ComandaService comandaService;

	/*
	 * Quando o cliente realiza a compra do ingresso é criado um objeto do mesmo, e
	 * respectivamente cria uma comanda, o metodo abaixo cria essa comanda.
	 */
	public void criarComanda(ClienteEntity clienteEntity, EventoEntity eventoEntity, Double precoIngresso) {

		ComandaEntity comandaEntity = new ComandaEntity();

		comandaService.salvar(comandaEntity, clienteEntity, eventoEntity, precoIngresso);
	}

}

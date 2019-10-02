package br.com.cng12.clubdance.utils;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.cng12.clubdance.entity.EventoEntity;
import br.com.cng12.clubdance.service.impl.ClienteServiceImpl;
import br.com.cng12.clubdance.service.impl.EventoServiceImpl;

public class ControleDeCapacidadeEvento {

	@Autowired
	private EventoServiceImpl eventoService;

	@Autowired
	private ClienteServiceImpl clienteService;

	void vendaIngressoNormalEVip(EventoEntity eventoEntity) {	
		
		EventoEntity evento = eventoService.buscarPorId(eventoEntity.getId());
		
		
	}

	void vendaIngressoVip() {

	}

	void vendaIngressoCamarote() {

	}

	/*
	 * O valor zerado significa que os 10 restantes são para os dois camarotes que
	 * possuem no local do evento
	 */
	public boolean verificaSeAQuantidadeDoEventoZerou(EventoEntity eventoEntity) {

		EventoEntity evento = eventoService.buscarPorId(eventoEntity.getId());

		if (evento.getCapacidade() == 10) {
			return true;
		} else {
			return false;
		}

	}

	void seTrocarTipoDeIngressoParaCamarote() {

	}

}

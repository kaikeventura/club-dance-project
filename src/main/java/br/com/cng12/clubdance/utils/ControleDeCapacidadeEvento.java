package br.com.cng12.clubdance.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cng12.clubdance.entity.ClienteEntity;
import br.com.cng12.clubdance.entity.EventoEntity;
import br.com.cng12.clubdance.service.impl.ClienteServiceImpl;
import br.com.cng12.clubdance.service.impl.EventoServiceImpl;

@Component
public class ControleDeCapacidadeEvento {

	@Autowired
	private EventoServiceImpl eventoService;

	@Autowired
	private ClienteServiceImpl clienteService;

	public void vendaIngressoNormalEVip(EventoEntity eventoEntity) {

		EventoEntity evento = eventoService.buscarPorId(eventoEntity.getId());

		int capacidadeAntes = evento.getCapacidade();
		int capacidadeDepois = capacidadeAntes - 1;

		eventoService.editarCapacidadeDoEvento(capacidadeDepois, evento.getId());

	}
	
	public void vendaIngressoCamarote(EventoEntity eventoEntity) {

		EventoEntity evento = eventoService.buscarPorId(eventoEntity.getId());

		int capacidadeAntes = evento.getCapacidade();
		int capacidadeDepois = capacidadeAntes - 5;

		eventoService.editarCapacidadeDoEvento(capacidadeDepois, evento.getId());

	}

	void vendaIngressoVip() {

	}

	void vendaIngressoCamarote() {

	}

	/*
	 * O valor zerado significa que os 15 restantes são para os três camarotes que
	 * possuem no local do evento
	 */
	public boolean verificaSeAQuantidadeDoEventoZerou(EventoEntity eventoEntity) {

		EventoEntity evento = eventoService.buscarPorId(eventoEntity.getId());

		if (evento.getCapacidade() == 15) {
			return true;
		} else {
			return false;
		}

	}

	public boolean verificaSeACapacidadeDoCamaroteExcedeu(EventoEntity eventoEntity) {
		List<ClienteEntity> clientes = clienteService.buscarClientesEvento(eventoEntity.getId(), "CAMAROTE");

		System.out.println(clientes.size());
		
		if (clientes.size() == 2) {
			
			return true;
		}
		return false;
	}

	void seTrocarTipoDeIngressoParaCamarote() {

	}

}

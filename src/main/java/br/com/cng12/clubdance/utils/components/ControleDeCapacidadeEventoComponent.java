package br.com.cng12.clubdance.utils.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cng12.clubdance.entity.ClienteEntity;
import br.com.cng12.clubdance.entity.EventoEntity;
import br.com.cng12.clubdance.exceptions.IngressoException;
import br.com.cng12.clubdance.service.impl.ComandaServiceImpl;
import br.com.cng12.clubdance.service.impl.EventoServiceImpl;

@Component
public class ControleDeCapacidadeEventoComponent {

	@Autowired
	private EventoServiceImpl eventoService;

	@Autowired
	private ComandaServiceImpl comandaService;

	public boolean vendaIngressoNormalEVip(EventoEntity eventoEntity) throws IngressoException {

		EventoEntity evento = eventoService.buscarPorId(eventoEntity.getId());
		int capacidadeAntes = evento.getCapacidade();

		if (!(capacidadeAntes == 0)) {
			int capacidadeDepois = capacidadeAntes - 1;
			eventoService.editarCapacidadeDoEvento(capacidadeDepois, evento.getId());
			return true;
		} else {
			return false;
		}
		

	}

	public boolean vendaIngressoCamarote(EventoEntity eventoEntity) throws IngressoException {

		EventoEntity evento = eventoService.buscarPorId(eventoEntity.getId());
		int capacidadeAntes = evento.getCapacidadeCamarote();

		if (!(capacidadeAntes == 0)) {
			int capacidadeDepois = capacidadeAntes - 1;
			eventoService.editarCapacidadeDoEventoCamarote(capacidadeDepois, evento.getId());
			return true;
		} else {
			return false;
		}

	}

	public void retornaIngressoNormalVip(EventoEntity eventoEntity) {

		EventoEntity evento = eventoService.buscarPorId(eventoEntity.getId());
		int capacidadeAntes = evento.getCapacidade();
		int capacidadeDepois = capacidadeAntes + 1;
		eventoService.editarCapacidadeDoEvento(capacidadeDepois, evento.getId());
	}
	
	public void retornaIngressoCamarote(EventoEntity eventoEntity) {

		EventoEntity evento = eventoService.buscarPorId(eventoEntity.getId());
		int capacidadeAntes = evento.getCapacidadeCamarote();
		int capacidadeDepois = capacidadeAntes + 1;
		eventoService.editarCapacidadeDoEventoCamarote(capacidadeDepois, evento.getId());
	}

	public void editaOValorDoIngressoTrocado(Double precoIngresso, ClienteEntity clienteEntity,
			EventoEntity eventoEntity) {
		
		comandaService.editarValorSeTrocarTipoDeIngresso(precoIngresso, clienteEntity, eventoEntity);
	}
}

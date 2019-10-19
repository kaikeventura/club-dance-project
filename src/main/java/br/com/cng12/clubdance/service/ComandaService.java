package br.com.cng12.clubdance.service;

import java.util.List;

import br.com.cng12.clubdance.entity.ClienteEntity;
import br.com.cng12.clubdance.entity.ComandaEntity;
import br.com.cng12.clubdance.entity.EventoEntity;

public interface ComandaService {

	void salvar(ComandaEntity comandaEntity, ClienteEntity ClienteEntity, EventoEntity eventoEntity,
			Double precoIngresso);

	List<ComandaEntity> buscarComandaComCliente(Long idCliente);

	void editarValorSeTrocarTipoDeIngresso(Double precoIngresso, ClienteEntity clienteEntity,
			EventoEntity eventoEntity);
	
	ComandaEntity buscarPorIdClienteIdEvento(ClienteEntity clienteEntity, EventoEntity eventoEntity);
}

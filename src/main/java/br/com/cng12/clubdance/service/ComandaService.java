package br.com.cng12.clubdance.service;

import br.com.cng12.clubdance.entity.ClienteEntity;
import br.com.cng12.clubdance.entity.ComandaEntity;
import br.com.cng12.clubdance.entity.EventoEntity;

public interface ComandaService {

	void salvar(ComandaEntity comandaEntity, ClienteEntity ClienteEntity, EventoEntity eventoEntity,
			Double precoIngresso);

}

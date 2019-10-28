package br.com.cng12.clubdance.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cng12.clubdance.dao.ComandaDAO;
import br.com.cng12.clubdance.entity.ClienteEntity;
import br.com.cng12.clubdance.entity.ComandaEntity;
import br.com.cng12.clubdance.entity.EventoEntity;
import br.com.cng12.clubdance.service.ComandaService;

@Service
public class ComandaServiceImpl implements ComandaService {

	@Autowired
	private ComandaDAO dao;

	@Override
	public void salvar(ComandaEntity comandaEntity, ClienteEntity clienteEntity, EventoEntity eventoEntity,
			Double precoIngresso) {

		comandaEntity.setClienteEntity(clienteEntity);
		comandaEntity.setEventoEntity(eventoEntity);
		comandaEntity.setPrecoIngresso(precoIngresso);

		dao.save(comandaEntity);
	}

	@Override
	public List<ComandaEntity> buscarComandaComCliente(Long idCliente) {

		return dao.buscarComandaComCliente(idCliente);
	}

	@Override
	public void editarValorSeTrocarTipoDeIngresso(Double precoIngresso, ClienteEntity clienteEntity,
			EventoEntity eventoEntity) {

		dao.editarValorSeTrocarTipoDeIngresso(precoIngresso, clienteEntity, eventoEntity);
	}

	@Override
	public ComandaEntity buscarPorIdClienteIdEvento(ClienteEntity clienteEntity, EventoEntity eventoEntity) {

		return dao.buscarPorIdClienteIdEvento(clienteEntity, eventoEntity);
	}

	@Override
	public ComandaEntity buscarComandaDoCliente(ClienteEntity clienteEntity) {

		return dao.buscarComandaDoCliente(clienteEntity);
	}

	@Override
	public void atualizaStatusComanda(String status, Long id) {

		dao.atualizaStatusComanda(status, id);
	}

}
